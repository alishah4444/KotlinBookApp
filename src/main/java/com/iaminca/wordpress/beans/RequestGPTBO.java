package com.iaminca.wordpress.beans;

import lombok.Data;

@Data
public class RequestGPTBO {
    private Long userId;
    private String gptKey;
    private String keySentence;
}
