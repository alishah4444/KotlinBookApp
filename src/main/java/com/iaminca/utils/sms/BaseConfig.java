package com.iaminca.utils.sms;


import org.springframework.util.StringUtils;

/**
 * Created by chenjin on 2020/4/13.
 */
public class BaseConfig {

    public static String report= "true";

    private static String PHONE_REGISTER="您的注册验证码是：code，不能告诉别人,10分钟有效";
    private static String BINDING_PHONE="您的绑定手机验证码是：code，不能告诉别人,10分钟有效";
    private static String FORGET_PASSWD="您的忘记密码验证码是：code，不能告诉别人,10分钟有效";
    private static String UPDATE_PHONE_FIRST="您的原手机号验证码：code，不能告诉别人,10分钟有效";
    private static String UPDATE_PHONE_SECOND="您的新手机号验证码：code，不能告诉别人,10分钟有效";
    private static String UPDATE_PASSWD="修改登录密码验证码：code，不能告诉别人,10分钟有效";
    private static String UPDATE_PAY_PASSWD_BYCODE="修改支付密码验证码：code，注意别人偷看,10分钟有效";
    private static String MERCHANT_WITHDRAW_BYCODE="提现验证码：code，注意别人偷看,10分钟有效";




    public static String getContent(SmsBusinessEnum smsBusinessEnum, SendSmsBO sendSmsBO){
        switch (smsBusinessEnum){
            case PHONE_REGISTER:
                return PHONE_REGISTER.replace("code",sendSmsBO.getCode());
            case BINDING_PHONE:
                return BINDING_PHONE.replace("code",sendSmsBO.getCode());
            case FORGET_PASSWD:
                return FORGET_PASSWD.replace("code",sendSmsBO.getCode());
            case UPDATE_PHONE_FIRST:
                return UPDATE_PHONE_FIRST.replace("code",sendSmsBO.getCode());
            case UPDATE_PASSWD:
                return UPDATE_PASSWD.replace("code",sendSmsBO.getCode());
            case UPDATE_PAY_PASSWD_BYCODE:
                return UPDATE_PAY_PASSWD_BYCODE.replace("code",sendSmsBO.getCode());
            case MERCHANT_WITHDRAW:
                return MERCHANT_WITHDRAW_BYCODE.replace("code",sendSmsBO.getCode());
            default:
                if (StringUtils.isEmpty(sendSmsBO.getContent())) {
                    return null;
                }else{
                    return sendSmsBO.getContent();
                }
        }


    }

}
