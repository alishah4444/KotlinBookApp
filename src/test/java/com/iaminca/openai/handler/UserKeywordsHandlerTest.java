package com.iaminca.openai.handler;

import com.iaminca.openai.OpenaiApplicationTests;
import com.iaminca.openai.common.Constants;
import com.iaminca.openai.common.model.PageListResult;
import com.iaminca.openai.query.UserKeywordsQuery;
import com.iaminca.openai.query.UserTaskInfoQuery;
import com.iaminca.openai.service.bo.UserKeywordsBO;
import com.iaminca.openai.service.bo.UserTaskInfoBO;
import com.iaminca.openai.wordpress.handlers.PushPostHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: auth
 * @description: AdminHandleTest
 * @create: 2023-05-13 00:58
 **/
@Slf4j
public class UserKeywordsHandlerTest extends OpenaiApplicationTests {
    @Resource
    private UserKeywordsHandler userKeywordsHandler;
    @Resource
    private UserTasksInfoHandler userTasksInfoHandler;

    @Resource
    private PushPostHandler pushPostHandler;

    @Test
    public void addUserKeywords() {
        UserKeywordsBO userBO = new UserKeywordsBO();
        userBO.setUserId(5015L);
        userBO.setKeywords("海南|北京|哈尔滨");
        userBO.setKeywordNumber(1);
        userBO.setApiKeyId(5048L);
        userBO.setUserKey("sk-rq5LLtN2H53I3NV46apiq3ftYDYwObj2RwEySSRFH14AuVU6");
        userBO.setAuthUsername("martian");
        userBO.setAuthPassword("kLyaeEqSU7ZkDgdbCUjh");
        userBO.setMaxLength(500);
        userBO.setCompletionTemplate("2023{0}夏天的旅游攻略");
        userBO.setPushUrl("https://wp.martian.tk/wp-json/wp/v2/posts");
        userKeywordsHandler.authorizing(userBO);
        System.out.println("DONE.");
    }


    @Test
    public void findUserKeywords() {
        UserKeywordsQuery userBO = new UserKeywordsQuery();
        userBO.setUserId(5015L);
        List<UserKeywordsBO> list = userKeywordsHandler.findList(userBO);
        log.info("DONE. {}", Constants.GSON.toJson(list));
        UserKeywordsQuery pageQuery = new UserKeywordsQuery();
        pageQuery.setUserId(5015L);
        PageListResult<UserKeywordsBO> handlerPage = userKeywordsHandler.findPage(pageQuery);
        log.info("DONE. {}", Constants.GSON.toJson(handlerPage));
    }

    @Test
    public void findUserKeywordsOne() {
        UserKeywordsQuery userBO = new UserKeywordsQuery();
        userBO.setUserId(1l);
        UserKeywordsBO one = userKeywordsHandler.findOne(userBO);
        log.info("DONE. {}", Constants.GSON.toJson(one));
    }

    @Test
    public void setPushPostHandler() {
        pushPostHandler.hookExecute(1L);
        log.info("DONE. {}");
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


    @Test
    public void mockUserTaskInfo() {
        UserTaskInfoQuery query = new UserTaskInfoQuery();
        query.setId(1L);
        UserTaskInfoBO one = userTasksInfoHandler.findOne(query);
        log.info("DONE.{}",Constants.GSON.toJson(one));

        UserKeywordsQuery queryKeywords = new UserKeywordsQuery();
        queryKeywords.setId(one.getUserKeywordsId());
        UserKeywordsBO keywordsOne = userKeywordsHandler.findOne(queryKeywords);
        log.info("DONE. keywordsOne : {}",Constants.GSON.toJson(keywordsOne));

        String pushUrl = keywordsOne.getPushUrl();
        String authPassword = keywordsOne.getAuthPassword();
        String authUsername = keywordsOne.getAuthUsername();
        Long apiKeyId = keywordsOne.getApiKeyId();
        

    }

}
