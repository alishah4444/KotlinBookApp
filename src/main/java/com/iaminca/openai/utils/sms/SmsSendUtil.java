package com.iaminca.openai.utils.sms;

import com.iaminca.openai.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * Created by chenjin on 2020/4/13.
 */
public class SmsSendUtil {

    private  Logger log = LoggerFactory.getLogger(SmsSendUtil.class);


    public SmsSendResponse sendSms(String phone, SmsBusinessEnum smsBusinessEnum, SendSmsBO sendSmsBO){
        if (StringUtils.isEmpty(phone)){
            log.error("SmsSendUtil.sendSms phone:{} ,smsBusinessEnum:{},sendSmsBO:{}",phone,smsBusinessEnum.name(), Constants.GSON.toJson(sendSmsBO));
//            throw new AppException(AppErrorCodeEnum.PHONE_IS_NULL);
            return null;
        }
        SmsSendRequest sendRequest=null;
        switch (smsBusinessEnum){
            case PHONE_REGISTER:
            case BINDING_PHONE:
            case FORGET_PASSWD:
            case UPDATE_PHONE_FIRST:
            case UPDATE_PASSWD:
            case UPDATE_PAY_PASSWD_BYCODE:
                if(StringUtils.isEmpty(sendSmsBO.getCode())){
                    log.error("SmsSendUtil.sendSms phone:{} ,smsBusinessEnum:{},sendSmsBO:{}",phone,smsBusinessEnum.name(), Constants.GSON.toJson(sendSmsBO));
//                    throw new AppException(AppErrorCodeEnum.AUTH_CAPTCHA_ERROR);
                    return null;
                }
                sendRequest= NoticeAndCodeConfig.getRequest(phone,smsBusinessEnum,sendSmsBO);
                break;

            default:
                sendRequest= NoticeAndCodeConfig.getRequest(phone,smsBusinessEnum,sendSmsBO);
                break;

        }
        return send(sendRequest);
    }


    private  SmsSendResponse send(SmsSendRequest smsSingleRequest){

        if (smsSingleRequest==null){
            log.info("before request smsSingleRequest is null " );
        }

        String requestJson = Constants.GSON.toJson(smsSingleRequest);
        log.info("before request string is: " + requestJson);

        String response = ChuangLanSmsUtil.sendSmsByPost(smsSingleRequest.getSmsSingleRequestServerUrl(), requestJson);

        SmsSendResponse smsSingleResponse = Constants.GSON.fromJson(response, SmsSendResponse.class);

        log.info("response  toString is :" + smsSingleResponse);

        return smsSingleResponse;
    }

    public static void main(String[] args) {

        SendSmsBO sendSmsBO=new SendSmsBO();
        sendSmsBO.setCode("678989");
        SmsSendUtil smsSendUtil=new SmsSendUtil();
        SmsSendResponse response= smsSendUtil.sendSms("15869007707",SmsBusinessEnum.PHONE_REGISTER,sendSmsBO);

        System.out.println(response);
    }

}
