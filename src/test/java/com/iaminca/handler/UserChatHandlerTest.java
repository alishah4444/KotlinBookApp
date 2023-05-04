package com.iaminca.handler;

import com.iaminca.OpenaiApplicationTests;
import com.iaminca.service.bo.UserBO;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: auth
 * @description: AdminHandleTest
 * @create: 2023-05-13 00:58
 **/
public class UserChatHandlerTest extends OpenaiApplicationTests {
    @Resource
    private ChatHandler chatHandler;


    @Test
    public void createChat() {
        ChatCompletionRequest request = new ChatCompletionRequest();
        request.setModel("gpt-3.5-turbo");
        request.setN(1);
        request.setMaxTokens(50);
        List<ChatMessage> messageList = new ArrayList();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setRole("user");
        chatMessage.setContent("来一首7言绝句");
        messageList.add(chatMessage);
        request.setMessages(messageList);

        chatHandler.createChatCompletion(request);
        System.out.println("DONE.");
    }
}
