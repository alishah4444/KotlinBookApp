package com.iaminca.client;

import com.theokanning.openai.completion.chat.ChatCompletionChunk;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.service.OpenAiService;
import io.reactivex.Flowable;
import org.springframework.stereotype.Component;

@Component
public class ChatClient {

    private final OpenAiService openAiService;

    public ChatClient(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    /**
     * Stream chat
     * @param request
     * @return
     */
    public Flowable<ChatCompletionChunk> streamChatCompletion(ChatCompletionRequest request){
        Flowable<ChatCompletionChunk> chatCompletionChunkFlowable = openAiService.streamChatCompletion(request);
        return chatCompletionChunkFlowable;
    }

    /**
     * No stream chat
     * @param request
     * @return
     */
    public ChatCompletionResult createChatCompletion(ChatCompletionRequest request){
        ChatCompletionResult chatCompletion = openAiService.createChatCompletion(request);
        return chatCompletion;
    }
}
