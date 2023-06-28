package com.iaminca.openai.utils.sms;

/**
 * Created by chenjin on 2020/4/13.
 */
public enum SmsBusinessEnum {

    PHONE_REGISTER(1,"注册验证码"),
    BINDING_PHONE(2,"绑定手机验证"),
    FORGET_PASSWD(3,"忘记密码验证码"),
    UPDATE_PHONE_FIRST(4, "修改绑定号码验证码"),
    UPDATE_PASSWD(5, "修改登录密码验证码"),
    UPDATE_PAY_PASSWD_BYCODE(6, "修改支付密码验证码"),

    SEND_SALSE(7, "发售后"),
    SEND_MSG(8, "发送通知消息"),

    SEND_TASK_APPLY(9, "派单通知"),

    MERCHANT_WITHDRAW(10, "商家提现"),

    ;


    private Integer code;
    private String msg;

    SmsBusinessEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
