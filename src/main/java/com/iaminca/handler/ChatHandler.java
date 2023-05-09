package com.iaminca.handler;

import com.iaminca.client.ChatClient;
import com.iaminca.common.Constants;
import com.iaminca.common.ErrorCode;
import com.iaminca.exception.BusinessException;
import com.iaminca.service.bo.ChatRequestBO;
import com.iaminca.service.bo.ChatResponseBO;
import com.iaminca.service.bo.ChatResponseChoicesBO;
import com.iaminca.service.bo.UserBalanceCacheBO;
import com.iaminca.service.covert.ChatRequestConvert;
import com.iaminca.utils.RedisKeyUtil;
import com.mysql.cj.util.StringUtils;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionChunk;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ChatHandler {

    private final ChatClient chatClient;
    private final ChatRequestHandler chatRequestHandler;
    private final ChatResponseHandler chatResponseHandler;
    private final StringRedisTemplate stringRedisTemplate;
    private final RedisTemplate redisTemplate;

    private void checkGptKey(String gptKey){
        String userIdCache = stringRedisTemplate.opsForValue().get(RedisKeyUtil.getGptKey(gptKey));
        if(StringUtils.isEmptyOrWhitespaceOnly(userIdCache)){
            throw new BusinessException(ErrorCode.GPT_KEY_ERROR);
        }
        String userBalance = stringRedisTemplate.opsForValue().get(RedisKeyUtil.getUserBalance(Long.valueOf(userIdCache)));
        if(Long.valueOf(userBalance) <= 0){
            throw new BusinessException(ErrorCode.GPT_BALANCE_ERROR);
        }
    }

    private void reduceGptTokens(Long userId,long tokensNumber){

//        String tokensBalance = stringRedisTemplate.opsForValue().get(RedisKeyUtil.getUserBalance(userId));
//        long balance = Long.valueOf(tokensBalance) - tokensNumber;
//        stringRedisTemplate.opsForValue().set(RedisKeyUtil.getUserBalance(userId),String.valueOf(balance));
        redisTemplate.opsForValue().decrement(RedisKeyUtil.getUserBalance(userId),tokensNumber);
    }

    private void saveChatCompletionChunk(ChatRequestBO chatRequestBO, List<ChatCompletionChunk> list) {
        System.out.println(Constants.GSON.toJson(chatRequestBO) + ": " + Constants.GSON.toJson(list));
    }

    /**
     * Stream chat
     * @param chatRequestBO
     * @return
     */
    public Flux<ChatCompletionChunk> streamChatCompletion(ChatRequestBO chatRequestBO) {
        ChatCompletionRequest request = ChatRequestConvert.toGptBO(chatRequestBO);

        Flux<ChatCompletionChunk> flux = Flux.from(chatClient.streamChatCompletion(request)).replay().autoConnect();

        return flux.concatWith(flux.collectList()
                .doOnSuccess(list -> this.saveChatCompletionChunk(chatRequestBO, list))
                .ignoreElement().dematerialize()
        );
    }

    /**
     * No stream chat
     * @param request
     * @return
     */
    public ChatCompletionResult createChatCompletion(ChatRequestBO request){

        //Check GPT balance
        this.checkGptKey(request.getGptKey());
        //Saving the content of request
        request.setModel(request.getModel());
        request.setUserId(request.getUserId());
        request.setKeyId(5000L);
        request.setStream(false);
        request.setModel("gpt-3.5-turbo");
        request.setN(1);
        request.setMaxTokens(1024);
        request.setUser(String.valueOf(request.getUserId()));
        chatRequestHandler.addChatRequest(request);


        ChatCompletionResult chatCompletion = chatClient.createChatCompletion(ChatRequestConvert.toGptBO(request));

        ChatResponseBO chatResponseBO = new ChatResponseBO();
        chatResponseBO.setUserId(request.getUserId());
        chatResponseBO.setKeyId(1L);
        chatResponseBO.setChatResponseId(chatCompletion.getId());
        chatResponseBO.setModel(chatCompletion.getModel());
        chatResponseBO.setCreated(chatCompletion.getCreated());
        chatResponseBO.setObject(chatCompletion.getObject());
        chatResponseBO.setUsageCompletionTokens(new Long(chatCompletion.getUsage().getCompletionTokens()).intValue());
        chatResponseBO.setUsagePromptTokens(new Long(chatCompletion.getUsage().getPromptTokens()).intValue());
        chatResponseBO.setUsageTotalTokens(new Long(chatCompletion.getUsage().getTotalTokens()).intValue());

        List<ChatResponseChoicesBO> chatResponseChoicesList =new ArrayList();
        for(ChatCompletionChoice choices : chatCompletion.getChoices()){
            ChatResponseChoicesBO chatResponseChoicesBO = new ChatResponseChoicesBO();
            chatResponseChoicesBO.setChatIndex(choices.getIndex());
            chatResponseChoicesBO.setMessageContent(choices.getMessage().getContent());
            chatResponseChoicesBO.setMessageFinishReason(choices.getFinishReason());
            chatResponseChoicesBO.setMessageRole(choices.getMessage().getRole());
            chatResponseChoicesList.add(chatResponseChoicesBO);
        }

        chatResponseHandler.addChatRequest(chatResponseBO,chatResponseChoicesList);
        //Reduce the user's tokens.
        this.reduceGptTokens(request.getUserId(),chatCompletion.getUsage().getTotalTokens());
        return chatCompletion;
    }
}
