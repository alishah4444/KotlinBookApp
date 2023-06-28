package com.iaminca.openai.service;

import com.iaminca.openai.OpenaiApplicationTests;
import com.iaminca.openai.common.Constants;
import com.iaminca.openai.service.bo.ChatResponseBO;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * @program: auth
 * @description: AdminHandleTest
 * @create: 2023-05-13 00:58
 **/
public class ChatResponseServiceTest extends OpenaiApplicationTests {
    @Resource
    private ChatResponseService chatResponseService;


    @Test
    public void addUser() {

        ChatResponseBO chatResponseBO = new ChatResponseBO();
        chatResponseBO.setUserId(5000L);
        chatResponseBO.setChatResponseId("completion-"+System.currentTimeMillis());
        chatResponseBO.setModel("model-test");
        chatResponseBO.setCreated(System.currentTimeMillis());
        chatResponseBO.setObject("Object-test");
        chatResponseBO.setUsageCompletionTokens(1);
        chatResponseBO.setUsagePromptTokens(2);
        chatResponseBO.setUsageTotalTokens(3);

        ChatResponseBO responseBO = chatResponseService.add(chatResponseBO);

        System.out.println("DONE. : "+ Constants.GSON.toJson(responseBO));
    }

}
