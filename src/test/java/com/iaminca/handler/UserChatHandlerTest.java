package com.iaminca.handler;

import com.iaminca.OpenaiApplicationTests;
import com.iaminca.service.bo.ChatRequestBO;
import com.iaminca.service.bo.ChatRequestMessageBO;
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
        ChatRequestBO request = new ChatRequestBO();
        request.setModel("gpt-3.5-turbo");
        request.setN(1);
        request.setMaxTokens(50);

        List<ChatRequestMessageBO> messageList = new ArrayList();
        ChatRequestMessageBO chatMessage = new ChatRequestMessageBO();
        chatMessage.setRole("user");
        chatMessage.setContent("来一首7言绝句");
        messageList.add(chatMessage);
        request.setMessages(messageList);

        chatHandler.createChatCompletion(request);
        System.out.println("DONE.");
    }
}
