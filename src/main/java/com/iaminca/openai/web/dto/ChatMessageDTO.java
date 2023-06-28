package com.iaminca.openai.web.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChatMessageDTO implements Serializable {
    String role;
    String content;
}
