package com.iaminca.openai.client;

import com.iaminca.openai.common.Constants;
import com.theokanning.openai.completion.chat.ChatCompletionChunk;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.service.OpenAiService;
import io.reactivex.Flowable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
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
        log.info("create Chat Completion stream request : {}", Constants.GSON.toJson(request));
        Flowable<ChatCompletionChunk> chatCompletionChunkFlowable = openAiService.streamChatCompletion(request);
        return chatCompletionChunkFlowable;
    }

    /**
     * No stream chat
     * @param request
     * @return
     */
    public ChatCompletionResult createChatCompletion(ChatCompletionRequest request){
        log.info("create Chat Completion request : {}", Constants.GSON.toJson(request));
        ChatCompletionResult chatCompletion = null;
        try{
            chatCompletion = openAiService.createChatCompletion(request);
        }catch (Exception e){
            return null;
        }
        log.info("create Chat Completion response : {}", Constants.GSON.toJson(chatCompletion));
        return chatCompletion;
    }
}
