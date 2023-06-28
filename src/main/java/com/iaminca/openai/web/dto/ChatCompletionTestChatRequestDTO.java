package com.iaminca.openai.web.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChatCompletionTestChatRequestDTO implements Serializable {

    private String message;
    private String gptKey;
}
