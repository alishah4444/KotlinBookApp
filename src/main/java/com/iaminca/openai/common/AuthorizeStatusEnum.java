package com.iaminca.openai.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthorizeStatusEnum {
    INIT(0,"Initial"),
    AUTHORIZING(1,"Authorized"),
    AUTHORIZED_PUSH(2,"Authorized Push"),




    ;

    private final Integer code;
    private final String message;

}
