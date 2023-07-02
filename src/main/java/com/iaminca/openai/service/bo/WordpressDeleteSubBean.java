package com.iaminca.openai.service.bo;

import lombok.Data;

@Data
public class WordpressDeleteSubBean {
    private Integer id;
    private String rendered;
    private WordpressSubBean title;
}
