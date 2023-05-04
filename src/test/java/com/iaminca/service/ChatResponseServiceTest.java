package com.iaminca.service;

import com.iaminca.OpenaiApplicationTests;
import com.iaminca.common.Constants;
import com.iaminca.service.bo.ChatResponseBO;
import com.iaminca.service.bo.UserBO;
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
        chatResponseBO.setChatModel("model-test");
        chatResponseBO.setChatCreated(System.currentTimeMillis());
        chatResponseBO.setChatObject("Object-test");
        chatResponseBO.setChatUsageCompletionTokens(1);
        chatResponseBO.setChatUsagePromptTokens(2);
        chatResponseBO.setChatUsageTotalTokens(3);

        ChatResponseBO responseBO = chatResponseService.add(chatResponseBO);

        System.out.println("DONE. : "+ Constants.GSON.toJson(responseBO));
    }

}
