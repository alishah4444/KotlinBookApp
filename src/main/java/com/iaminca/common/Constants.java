package com.iaminca.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Constants {
    public static Gson GSON = new GsonBuilder().create();


    public static String USER_REDIS_REGISTER_DIRE = "user:register:";
    public static String USER_REDIS_INFO_DIRE = "user:info:";



}
