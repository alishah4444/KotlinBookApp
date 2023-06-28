package com.iaminca.openai.handler;

import com.iaminca.openai.OpenaiApplicationTests;
import com.iaminca.openai.common.Constants;
import com.iaminca.openai.service.bo.ChatRequestBO;
import com.iaminca.openai.service.bo.ChatRequestMessageBO;
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
        request.setModel(Constants.GPT_CHAT_MODEL);
        request.setN(1);
        request.setMaxTokens(50);
        request.setGptKey("sk-rq5LLtN2H53I3NV46apiq3ftYDYwObj2RwEySSRFH14AuVU6");
        request.setUserId(5015L);
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
