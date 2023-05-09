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
import io.reactivex.Flowable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

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

    private UserBalanceCacheBO checkGptKey(String gptKey){
        String  userIdCache = stringRedisTemplate.opsForValue().get(RedisKeyUtil.userInfoKey(gptKey));
        if(StringUtils.isEmptyOrWhitespaceOnly(userIdCache)){
            throw new BusinessException(ErrorCode.GPT_KEY_ERROR);
        }
        String userBalance = stringRedisTemplate.opsForValue().get(RedisKeyUtil.getUserBalance(Long.valueOf(userIdCache)));
        UserBalanceCacheBO userBalanceCache = Constants.GSON.fromJson(userBalance, UserBalanceCacheBO.class);
        if(ObjectUtils.isEmpty(userBalanceCache) || userBalanceCache.getUserTokensBalance()<= 0){
            throw new BusinessException(ErrorCode.GPT_BALANCE_ERROR);
        }
        return userBalanceCache;
    }

    private void reduceGptTokens(Long userId,long tokensNumber){

        redisTemplate.opsForValue().decrement(RedisKeyUtil.getUserBalance(userId),tokensNumber);
    }
    /**
     * Stream chat
     * @param chatRequestBO
     * @return
     */
    public Flowable<ChatCompletionChunk> streamChatCompletion(ChatRequestBO chatRequestBO){
        ChatCompletionRequest request = ChatRequestConvert.toGptBO(chatRequestBO);
        Flowable<ChatCompletionChunk> chatCompletionChunkFlowable = chatClient.streamChatCompletion(request);
        return chatCompletionChunkFlowable;
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
