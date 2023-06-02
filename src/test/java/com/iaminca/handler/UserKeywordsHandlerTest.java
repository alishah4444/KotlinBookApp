package com.iaminca.handler;

import com.iaminca.OpenaiApplicationTests;
import com.iaminca.service.bo.UserBO;
import com.iaminca.service.bo.UserKeywordsBO;
import com.iaminca.service.bo.UserTaskInfoBO;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * @program: auth
 * @description: AdminHandleTest
 * @create: 2023-05-13 00:58
 **/
public class UserKeywordsHandlerTest extends OpenaiApplicationTests {
    @Resource
    private UserKeywordsHandler userKeywordsHandler;
    @Resource
    private UserTasksInfoHandler userTasksInfoHandler;


    @Test
    public void addUser() {
        UserKeywordsBO userBO = new UserKeywordsBO();
        userBO.setUserId(5015L);
        userBO.setKeywords("海南|北京|哈尔滨");
        userBO.setKeywordNumber(1);
        userBO.setApiKeyId(5048L);
        userBO.setAuthUsername("martian");
        userBO.setAuthPassword("kLyaeEqSU7ZkDgdbCUjh");
        userBO.setMaxLength(500);
        userBO.setCompletionTemplate("2023{place}夏天的旅游攻略");
        userBO.setPushUrl("https://wp.martian.tk/wp-json/wp/v2/posts");
        userKeywordsHandler.insert(userBO);
        System.out.println("DONE.");
    }

    @Test
    public void addUserTaskInfo() {
        UserTaskInfoBO userBO = new UserTaskInfoBO();
        userBO.setUserId(5015L);
        userBO.setCron("10 * * * ?");
        userBO.setTaskStatus(1);
        userBO.setProcessNumber(0);
        userBO.setUserKeywordsId(1L);
        userTasksInfoHandler.insert(userBO);
        System.out.println("DONE.");
    }

}
