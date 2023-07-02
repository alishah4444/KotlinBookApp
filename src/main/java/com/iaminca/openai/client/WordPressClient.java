package com.iaminca.openai.client;

import com.iaminca.openai.common.Constants;
import com.iaminca.openai.utils.HttpUtil;
import com.iaminca.openai.wordpress.beans.WordpressTaskBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class WordPressClient {


    /**
     * Push post to website
     * @param wordpressTaskBean
     * @return
     */
    public String pushPosts(String url,String authUsername,String authPassword,WordpressTaskBean wordpressTaskBean){
        log.info("WordPressClient pushPosts : {}", Constants.GSON.toJson(wordpressTaskBean));
        String responseResult = "";
        try{
            String json = Constants.GSON.toJson(wordpressTaskBean);
            Map<String, Object> headers = new HashMap<>();
            headers.put("Authorization", "Basic "+ Base64.getEncoder().encodeToString((authUsername+":"+authPassword).getBytes(Constants.CHART_TYPE)));
            responseResult = HttpUtil.sendPostJson(url, json, headers);
        }catch (Exception e){
            log.error("WordPressClient pushPosts Exception: {}",e);
            return null;
        }
        log.info("WordPressClient pushPosts response : {}", Constants.GSON.toJson(responseResult));
        return responseResult;
    }

    /**
     * Delete a post in website
     * @param postId
     * @return
     */
    public String deletePosts(String url,String authUsername,String authPassword){
        log.info("WordPressClient deletePosts : {}", url);
        String responseResult = "";
        try{
            Map<String, Object> headers = new HashMap<>();
            headers.put("Authorization", "Basic "+ Base64.getEncoder().encodeToString((authUsername+":"+authPassword).getBytes(Constants.CHART_TYPE)));
            responseResult = HttpUtil.sendDelete(url, headers);
        }catch (Exception e){
            log.error("WordPressClient pushPosts Exception: {}",e);
            return null;
        }
        log.info("WordPressClient pushPosts response : {}", Constants.GSON.toJson(responseResult));
        return responseResult;
    }
}
