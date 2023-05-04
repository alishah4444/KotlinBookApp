package com.iaminca.service;

import com.iaminca.OpenaiApplicationTests;
import com.iaminca.service.bo.UserBO;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * @program: auth
 * @description: AdminHandleTest
 * @create: 2023-05-13 00:58
 **/
public class AdminServiceTest extends OpenaiApplicationTests {
    @Resource
    private UserService userService;


    @Test
    public void addUser() {
        UserBO userBO = new UserBO();
        userBO.setUserName("test");
        userBO.setUserPhone("519");
        userBO.setUserType("User");
        userBO.setUserChatLimitation(100);
        userBO.setUserLengthLimitation(200);
        userService.add(userBO);
//        System.out.println(token);
        System.out.println("DONE.");
    }

}
