package com.iaminca.utils;

import com.iaminca.common.Constants;

public class RedisKeyUtil {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String registerCodeKey(String verification) {
       return Constants.USER_REDIS_REGISTER_DIRE+verification;
    }
    public static String userInfoKey(String token) {
        return Constants.USER_REDIS_INFO_DIRE+token;
    }

    public static String getGptKey(String gptKey) {
        return Constants.GPT_REDIS_USERINFO_DIRE+gptKey;
    }

    public static String getUserBalance(Long userID) {
        return Constants.GPT_REDIS_USER_BALANCE_DIRE+userID;
    }
}
