package com.iaminca.openai.utils.sms;


import org.springframework.util.StringUtils;

/**
 * Created by chenjin on 2020/4/13.
 *
 * 创蓝验证码通知类短信配置
 */
public class NoticeAndCodeConfig extends BaseConfig {
    private static final String charset = "utf-8";

    private static String account = "N1065914";
    private static String password = "br3C1qcwe";
    private static String smsSingleRequestServerUrl = "http://smssh1.253.com/msg/v1/send/json";
//    private static String smsSingleRequestServerUrl = "https://zz.253.com/site/sublogin.html";


    public static SmsSendRequest getRequest(String phone, SmsBusinessEnum smsBusinessEnum, SendSmsBO sendSmsBO){
        String msg= getContent(smsBusinessEnum,sendSmsBO);
        if (StringUtils.isEmpty(msg)){
            return null;
        }
        SmsSendRequest smsSendRequest = new SmsSendRequest(account, password, "【云端数控】" + msg, phone, report);
        smsSendRequest.setSmsSingleRequestServerUrl(smsSingleRequestServerUrl);
        return smsSendRequest;
    }
}
