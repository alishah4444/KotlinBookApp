package com.iaminca.handler;

import com.iaminca.client.ChatClient;
import com.iaminca.common.Constants;
import com.iaminca.common.ErrorCode;
import com.iaminca.exception.BusinessException;
import com.iaminca.service.bo.*;
import com.iaminca.service.covert.ChatRequestConvert;
import com.iaminca.utils.IDUtil;
import com.iaminca.utils.RedisKeyUtil;
import com.mysql.cj.util.StringUtils;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionChunk;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ChatHandler {

    private final ChatClient chatClient;
    private final ChatRequestHandler chatRequestHandler;
    private final ChatResponseHandler chatResponseHandler;
    private final ChatTokensCalculationHandler chatTokensCalculationHandler;


    /**
     * Stream chat
     * @param request
     * @return
     */
    public Flux<ChatCompletionChunk> streamChatCompletion(ChatRequestBO request) {
        log.info("stream chat streamChatCompletion");
        //Check GPT balance
        UserKeyBO userKeyBO = chatTokensCalculationHandler.checkGptKey(request.getGptKey());
        String recordCycleID = IDUtil.getRecordCycleID();
        request.setRecordId(recordCycleID);
        request.setKeyId(userKeyBO.getId());
        request.setStream(true);
        request.setModel(Constants.GPT_CHAT_MODEL);
        request.setN(Constants.GPT_CHAT_N);
        request.setMaxTokens(Constants.GPT_CHAT_MAX_TOKENS);
        request.setUser(String.valueOf(request.getUserId()));
        chatRequestHandler.addChatRequest(request);

        ChatCompletionRequest chatRequest = ChatRequestConvert.toGptBO(request);
        //Saving the content of request

        Flux<ChatCompletionChunk> flux = Flux.from(chatClient.streamChatCompletion(chatRequest)).replay().autoConnect();

        return flux.concatWith(flux.collectList()
                .doOnSuccess(list -> chatTokensCalculationHandler.saveChatCompletionChunk(request, list))
                .ignoreElement().dematerialize()
        );
    }

    /**
     * No stream chat
     * @param request
     * @return
     */
    public ChatCompletionResult createChatCompletion(ChatRequestBO request){
        log.info("unstream chat createChatCompletion");
        //Check GPT balance
        UserKeyBO userKeyBO = chatTokensCalculationHandler.checkGptKey(request.getGptKey());
        //Saving the content of request
        String recordCycleID = IDUtil.getRecordCycleID();
        request.setRecordId(recordCycleID);
        request.setKeyId(userKeyBO.getId());
        request.setStream(false);
        request.setModel(Constants.GPT_CHAT_MODEL);
        request.setN(Constants.GPT_CHAT_N);
        request.setMaxTokens(Constants.GPT_CHAT_MAX_TOKENS);
        request.setUser(String.valueOf(request.getUserId()));
        chatRequestHandler.addChatRequest(request);


        ChatCompletionResult chatCompletion = chatClient.createChatCompletion(ChatRequestConvert.toGptBO(request));

        ChatResponseBO chatResponseBO = new ChatResponseBO();
        chatResponseBO.setRecordId(recordCycleID);
        chatResponseBO.setUserId(request.getUserId());
        chatResponseBO.setKeyId(userKeyBO.getId());
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
        chatTokensCalculationHandler.reduceGptTokens(request.getUserId(),chatCompletion.getUsage().getTotalTokens());
        return chatCompletion;
    }
}
