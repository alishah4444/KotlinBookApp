package com.iaminca.openai.handler;

import com.iaminca.openai.OpenaiApplicationTests;
import com.iaminca.openai.service.bo.UserBO;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * @program: auth
 * @description: AdminHandleTest
 * @create: 2023-05-13 00:58
 **/
public class UserHandlerTest extends OpenaiApplicationTests {
    @Resource
    private UserHandler userHandler;


    @Test
    public void addUser() {
        UserBO userBO = new UserBO();
        userBO.setUserName("test"+System.currentTimeMillis());
        userBO.setUserPhone("519");
        userBO.setUserType("User");
        userBO.setUserChatLimitation(100);
        userBO.setUserLengthLimitation(200);
        userHandler.sendCode(userBO);
        System.out.println("DONE.");
    }

}
