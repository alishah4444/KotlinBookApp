package com.iaminca.openai.handler;

import com.iaminca.openai.client.ChatClient;
import com.iaminca.openai.common.Constants;
import com.iaminca.openai.service.bo.ChatRequestBO;
import com.iaminca.openai.service.bo.ChatResponseBO;
import com.iaminca.openai.service.bo.ChatResponseChoicesBO;
import com.iaminca.openai.service.covert.ChatRequestConvert;
import com.iaminca.openai.utils.IDUtil;
import com.iaminca.openai.service.bo.UserKeyBO;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionChunk;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
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
        request.setN(request.getN() !=null? request.getN() : Constants.GPT_CHAT_N);
        request.setMaxTokens(request.getMaxTokens() != null? request.getMaxTokens() : Constants.GPT_CHAT_MAX_TOKENS);
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

        ChatRequestBO chatRequestBO = this.saveRequest(request, userKeyBO.getId());

        ChatCompletionResult chatCompletion = chatClient.createChatCompletion(ChatRequestConvert.toGptBO(request));

        this.saveResponse(chatRequestBO,chatCompletion);
        //Reduce the user's tokens.
        chatTokensCalculationHandler.reduceGptTokens(request.getUserId(),chatCompletion.getUsage().getTotalTokens());
        return chatCompletion;
    }

    /**
     * No stream chat, return the response result
     * @param request
     * @return ChatResponseBO
     */
    public ChatResponseBO createChatCompletion2(ChatRequestBO request){
        log.info("unstream chat createChatCompletion");
        //Check GPT balance
        UserKeyBO userKeyBO = chatTokensCalculationHandler.checkGptKey(request.getGptKey());
        ChatRequestBO chatRequestBO = this.saveRequest(request, userKeyBO.getId());


        ChatCompletionResult chatCompletion = chatClient.createChatCompletion(ChatRequestConvert.toGptBO(request));
        if(chatCompletion == null){
            return null;
        }
        ChatResponseBO chatResponseBO = this.saveResponse(chatRequestBO, chatCompletion);
        //Reduce the user's tokens.
        chatTokensCalculationHandler.reduceGptTokens(request.getUserId(),chatCompletion.getUsage().getTotalTokens());
        return chatResponseBO;
    }


    private ChatRequestBO saveRequest(ChatRequestBO request,Long keyId){
        //Saving the content of request
        String recordCycleID = IDUtil.getRecordCycleID();
        request.setRecordId(recordCycleID);
        request.setKeyId(keyId);
        request.setStream(false);
        request.setModel(Constants.GPT_CHAT_MODEL);
        request.setN(Constants.GPT_CHAT_N);
        request.setMaxTokens(Constants.GPT_CHAT_MAX_TOKENS);
        request.setUser(String.valueOf(request.getUserId()));
        return chatRequestHandler.addChatRequest(request);
    }

    private ChatResponseBO saveResponse(ChatRequestBO chatRequestBO,ChatCompletionResult chatCompletion){

        ChatResponseBO chatResponseBO = new ChatResponseBO();
        chatResponseBO.setRecordId(chatRequestBO.getRecordId());
        chatResponseBO.setUserId(chatRequestBO.getUserId());
        chatResponseBO.setKeyId(chatRequestBO.getKeyId());
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

        ChatResponseBO chatResponseBO1 = chatResponseHandler.addChatRequest(chatResponseBO, chatResponseChoicesList);
        return chatResponseBO1;
    }
}
