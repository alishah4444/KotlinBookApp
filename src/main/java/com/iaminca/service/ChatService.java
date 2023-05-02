package com.iaminca.service;

import com.theokanning.openai.completion.chat.ChatCompletionChunk;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import io.reactivex.Flowable;

public interface ChatService {

    /**
     * Stream chat
     *
     * @param request
     * @return
     */
    public Flowable<ChatCompletionChunk> streamChatCompletion(ChatCompletionRequest request);

    /**
     * No stream chat
     *
     * @param request
     * @return
     */
    public ChatCompletionResult createChatCompletion(ChatCompletionRequest request);
}
