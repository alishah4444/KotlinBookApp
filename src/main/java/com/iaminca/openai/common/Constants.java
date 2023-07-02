package com.iaminca.openai.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Constants {
    public static Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:sss").create();
    //查询排序字段
    public static String  ORDER_BY = "update_time desc";


    public static String GPT_CHAT_MODEL = "gpt-3.5-turbo";
    public static String GPT_CHAT_ROLE = "user";
    public static Integer GPT_CHAT_N = 1;
    public static Integer GPT_CHAT_MAX_TOKENS = 2048;
    public final static String CHART_TYPE = "utf-8";

    public static String WORDPRESS_JSON_POSTS_URL =  "/wp-json/wp/v2/posts";

}
