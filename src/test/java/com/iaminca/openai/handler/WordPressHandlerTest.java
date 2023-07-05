package com.iaminca.openai.handler;

import com.iaminca.openai.OpenaiApplicationTests;
import com.iaminca.openai.common.Constants;
import com.iaminca.openai.service.bo.UserKeywordsBO;
import com.iaminca.openai.service.bo.WordpressDeleteResponseBean;
import com.iaminca.openai.service.bo.WordpressPostResponseBean;
import com.iaminca.openai.utils.DateUtil;
import com.iaminca.openai.wordpress.beans.WordpressTaskBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @program: auth
 * @description: AdminHandleTest
 * @create: 2023-05-13 00:58
 **/
@Slf4j
public class WordPressHandlerTest extends OpenaiApplicationTests {
    @Resource
    private WordPressHandler wordPressHandler;


    @Test
    public void postArtcle() {

        UserKeywordsBO insertBO = new UserKeywordsBO();
        insertBO.setUserId(1L);
//        insertBO.setPushUrl("https://wp.martian.tk");
//        insertBO.setAuthUsername("martian");
//        insertBO.setAuthPassword("kLyaeEqSU7ZkDgdbCUjh");
        insertBO.setPushUrl("http://104.249.156.179");
        insertBO.setAuthUsername("twoapi.com");
        insertBO.setAuthPassword("twoapi888");

        WordpressTaskBean wordpressTaskBean = new WordpressTaskBean();
        wordpressTaskBean.setDate(DateUtil.formDate(new Date(),DateUtil.DATETIME_FORMAT_PATTERN));
        wordpressTaskBean.setDate_gmt(DateUtil.formDate(new Date(),DateUtil.DATETIME_FORMAT_PATTERN));
        wordpressTaskBean.setSlug("aiPost");
        wordpressTaskBean.setStatus("publish");
        wordpressTaskBean.setTitle("AI 自动托管授权");
        wordpressTaskBean.setContent("AI 自动托管授权");
        wordpressTaskBean.setComment_status("closed");
        wordpressTaskBean.setFeatured_media(1);
        wordpressTaskBean.setPing_status("open");
        wordpressTaskBean.setFormat("standard");

        WordpressPostResponseBean wordpressPostResponseBean = wordPressHandler.pushPost(insertBO.getPushUrl(), insertBO.getAuthUsername(), insertBO.getAuthPassword(), wordpressTaskBean);
        log.error(Constants.GSON.toJson(wordpressPostResponseBean));

        System.out.println("DONE.");
    }



    @Test
    public void deleteArtcle() {

        UserKeywordsBO insertBO = new UserKeywordsBO();
        insertBO.setUserId(1L);
        insertBO.setPushUrl("http://localhost/twoapi-wordpress");
        insertBO.setAuthUsername("twoapi.com");
        insertBO.setAuthPassword("twoapi888");


        WordpressDeleteResponseBean wordpressPostResponseBean = wordPressHandler.deletePost(insertBO.getPushUrl(), insertBO.getAuthUsername(), insertBO.getAuthPassword(), 4540);
        log.error(Constants.GSON.toJson(wordpressPostResponseBean));

        System.out.println("DONE.");
    }


}
