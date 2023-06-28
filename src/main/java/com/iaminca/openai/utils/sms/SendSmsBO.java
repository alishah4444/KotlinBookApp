package com.iaminca.openai.utils.sms;

import java.io.Serializable;

/**
 * Created by chenjin on 2020/4/13.
 */
public class SendSmsBO implements Serializable {

    private String code;

    private String content;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
