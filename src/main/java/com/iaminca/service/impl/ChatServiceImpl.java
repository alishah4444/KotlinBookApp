package com.iaminca.service.impl;

import com.iaminca.client.ChatClient;
import com.iaminca.service.ChatService;
import com.theokanning.openai.completion.chat.ChatCompletionChunk;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import io.reactivex.Flowable;
import org.springframework.stereotype.Component;

@Component
public class ChatServiceImpl implements ChatService {

    private ChatClient chatClient;
    /**
     * Stream chat
     *
     * @param request
     * @return
     */
    public Flowable<ChatCompletionChunk> streamChatCompletion(ChatCompletionRequest request){

        Flowable<ChatCompletionChunk> chatCompletionChunkFlowable = chatClient.streamChatCompletion(request);
        return chatCompletionChunkFlowable;
    }

    /**
     * No stream chat
     *
     * @param request
     * @return
     */
    public ChatCompletionResult createChatCompletion(ChatCompletionRequest request){
        ChatCompletionResult chatCompletion = chatClient.createChatCompletion(request);
        return chatCompletion;
    }
}
