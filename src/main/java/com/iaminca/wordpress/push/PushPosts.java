package com.iaminca.wordpress.push;


import com.iaminca.common.Constants;
import com.iaminca.utils.DateUtil;
import com.iaminca.utils.HttpUtil;
import com.iaminca.wordpress.beans.WordpressTaskBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class PushPosts {

    private final static String chart_type = "utf-8";

    /**
     * Execute the push post process.
     * @param url
     * @param authUsername
     * @param authPassword
     * @param title
     * @param content
     * @param categories
     */
    public void executePush(String url,String authUsername,String authPassword,String title,String content,int[] categories){
        WordpressTaskBean wordpressTaskBean = new WordpressTaskBean();
        wordpressTaskBean.setDate(DateUtil.formDate(new Date(),DateUtil.DATETIME_FORMAT_PATTERN));
        wordpressTaskBean.setDate_gmt(DateUtil.formDate(new Date(),DateUtil.DATETIME_FORMAT_PATTERN));
        wordpressTaskBean.setSlug("my-post");
        wordpressTaskBean.setStatus("publish");
//        wordpressTaskBean.setPassword();
        wordpressTaskBean.setTitle(title);
        wordpressTaskBean.setContent(content);
//        wordpressTaskBean.setAuthor(1);
//        wordpressTaskBean.setExcerpt();
        wordpressTaskBean.setComment_status("closed");
        wordpressTaskBean.setFeatured_media(1);
        wordpressTaskBean.setPing_status("open");
        wordpressTaskBean.setFormat("standard");
//        wordpressTaskBean.setMeta();
        wordpressTaskBean.setCategories(categories);
        try{
            String json = Constants.GSON.toJson(wordpressTaskBean);
            Map<String, Object> headers = new HashMap<>();
            headers.put("Authorization", "Basic "+ Base64.getEncoder().encodeToString((authUsername+":"+authPassword).getBytes(chart_type)));
            String responseResult = HttpUtil.sendPostJson(url, json, headers);
            log.info(responseResult);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
