package com.iaminca.utils;

public class RedisKeyUtil {


    public static String USER_REDIS_REGISTER_DIRE = "user:register:";
    public static String USER_REDIS_INFO_DIRE = "user:info:";
    public static String USER_REDIS_PHONE_TOKEN_DIRE = "user:info:phone:token:";

    public static String GPT_REDIS_USERINFO_DIRE = "gpt:user:info:";
    public static String GPT_REDIS_USER_BALANCE_DIRE = "gpt:user:balance:";


    public static String GPT_REDIS_RECORD_ID_CYCLE = "gpt:record:id:cycle";


    public static String registerCodeKey(String verification) {
       return USER_REDIS_REGISTER_DIRE+verification;
    }
    public static String userInfoKey(String token) {
        return USER_REDIS_INFO_DIRE+token;
    }
    public static String phoneAndToken(String phone) {
        return USER_REDIS_PHONE_TOKEN_DIRE+phone;
    }

    public static String getGptKey(String gptKey) {
        return GPT_REDIS_USERINFO_DIRE+gptKey;
    }

    public static String getUserBalance(Long userID) {
        return GPT_REDIS_USER_BALANCE_DIRE+userID;
    }

    public static String getRecordIdSuffix() {
        return GPT_REDIS_RECORD_ID_CYCLE;
    }
}
