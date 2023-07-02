package com.iaminca.openai.service.bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class WordpressPostResponseBean implements Serializable {
    private Integer id;
    private String date_gmt;
    private WordpressSubBean guid; // rendered,raw,
    private String modified;
    private String modified_gmt;
    private String type;
    private String link;

    private String slug;
    private String status;
    private String password;
    private WordpressSubBean title; // raw,rendered,
    private WordpressSubBean content; //raw,rendered,

    private Integer author;
    private WordpressSubBean excerpt;
    private String comment_status;
    private Integer featured_media;
    private String ping_status;
    private String format;
    private String[] meta;
    private int[] categories;
    private int[] tags;
     private Integer pushStatus;//0 success.
}
