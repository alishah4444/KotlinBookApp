package com.iaminca.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserTypeEnum {
    USER("user","normal tried user"),
    VIP("vip","monthly"),
    SVIP("svip","yearly"),




    ;

    private final String code;
    private final String message;

}
