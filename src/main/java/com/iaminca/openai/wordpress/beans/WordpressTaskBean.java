package com.iaminca.openai.wordpress.beans;

import lombok.Data;

import java.io.Serializable;

@Data
public class WordpressTaskBean implements Serializable {
    private String date;
    private String date_gmt;
    private String slug;
    private String status;
    private String password;
    private String title;
    private String content;
    private Integer author;
    private String excerpt;
    private String comment_status;
    private Integer featured_media;
    private String ping_status;
    private String format;
    private String meta;
    private int[] categories;
    private Integer pushStatus;//0 success.
}