package com.iaminca.openai.service.bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class WordpressDeleteResponseBean implements Serializable {
    private Boolean deleted;
    private WordpressDeleteSubBean wordpressDeleteSubBean;

}
