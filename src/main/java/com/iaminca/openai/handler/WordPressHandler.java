package com.iaminca.openai.handler;

import com.iaminca.openai.client.WordPressClient;
import com.iaminca.openai.common.Constants;
import com.iaminca.openai.common.ErrorCode;
import com.iaminca.openai.exception.BusinessException;
import com.iaminca.openai.service.bo.WordpressDeleteResponseBean;
import com.iaminca.openai.service.bo.WordpressPostResponseBean;
import com.iaminca.openai.wordpress.beans.WordpressTaskBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:22
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class WordPressHandler {

    @Resource
    private WordPressClient wordPressClient;


    public WordpressPostResponseBean pushPost(String url, String authUsername, String authPassword, WordpressTaskBean wordpressTaskBean){
        if(StringUtils.isEmpty(url) || StringUtils.isEmpty(authUsername) || StringUtils.isEmpty(authPassword) || ObjectUtils.isEmpty(wordpressTaskBean)){
            throw new BusinessException(ErrorCode.PARAM_IS_ERROR);
        }
        String result = wordPressClient.pushPosts(url + Constants.WORDPRESS_JSON_POSTS_URL, authUsername, authPassword, wordpressTaskBean);
        if(result == null){
            throw new BusinessException(ErrorCode.AUTHORIZE_ERROR);
        }
        return Constants.GSON.fromJson(result, WordpressPostResponseBean.class);
    }


    public WordpressDeleteResponseBean deletePost(String url, String authUsername, String authPassword, Integer postId){
        if(StringUtils.isEmpty(url) || StringUtils.isEmpty(authUsername) || StringUtils.isEmpty(authPassword) || ObjectUtils.isEmpty(postId)){
            throw new BusinessException(ErrorCode.PARAM_IS_ERROR);
        }
        String result = wordPressClient.deletePosts(url + Constants.WORDPRESS_JSON_POSTS_URL+"/"+postId+"?force=true", authUsername, authPassword);
        if(result == null){
            throw new BusinessException(ErrorCode.AUTHORIZE_ERROR);
        }
        return Constants.GSON.fromJson(result, WordpressDeleteResponseBean.class);
    }

}
