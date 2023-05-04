package com.iaminca.handler;

import com.iaminca.client.ChatClient;
import com.iaminca.service.bo.ChatRequestBO;
import com.iaminca.service.bo.ChatResponseBO;
import com.iaminca.service.bo.ChatResponseChoicesBO;
import com.iaminca.service.covert.ChatRequestConvert;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionChunk;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import io.reactivex.Flowable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ChatHandler {

    private final ChatClient chatClient;
    private final ChatRequestHandler chatRequestHandler;
    private final ChatResponseHandler chatResponseHandler;

    /**
     * Stream chat
     * @param request
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

        //Saving the content of request
        request.setModel(request.getModel());
        request.setUserId(5000L);
        request.setKeyId(5000L);
        request.setStream(false);
        chatRequestHandler.addChatRequest(request);


        ChatCompletionResult chatCompletion = chatClient.createChatCompletion(ChatRequestConvert.toGptBO(request));

        ChatResponseBO chatResponseBO = new ChatResponseBO();
        chatResponseBO.setUserId(5000L);
        chatResponseBO.setChatResponseId(chatCompletion.getId());
        chatResponseBO.setChatModel(chatCompletion.getModel());
        chatResponseBO.setChatCreated(chatCompletion.getCreated());
        chatResponseBO.setChatObject(chatCompletion.getObject());
        chatResponseBO.setChatUsageCompletionTokens(new Long(chatCompletion.getUsage().getCompletionTokens()).intValue());
        chatResponseBO.setChatUsagePromptTokens(new Long(chatCompletion.getUsage().getPromptTokens()).intValue());
        chatResponseBO.setChatUsageTotalTokens(new Long(chatCompletion.getUsage().getTotalTokens()).intValue());

        List<ChatResponseChoicesBO> chatResponseChoicesList =new ArrayList();
        for(ChatCompletionChoice choices : chatCompletion.getChoices()){
            ChatResponseChoicesBO chatResponseChoicesBO = new ChatResponseChoicesBO();
            chatResponseChoicesBO.setChoicesIndex(choices.getIndex());
            chatResponseChoicesBO.setChoicesMessageContent(choices.getMessage().getContent());
            chatResponseChoicesBO.setChoicesMessageFinishReason(choices.getFinishReason());
            chatResponseChoicesBO.setChoicesMessageRole(choices.getMessage().getRole());
            chatResponseChoicesList.add(chatResponseChoicesBO);
        }

        chatResponseHandler.addChatRequest(chatResponseBO,chatResponseChoicesList);
        return chatCompletion;
    }
}
