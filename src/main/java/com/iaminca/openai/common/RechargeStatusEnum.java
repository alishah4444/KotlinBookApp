package com.iaminca.openai.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RechargeStatusEnum {
    WAITING(0,"Waiting"),
    PASS(1,"Passed recharge"),
    REFUSING(9,"Refused recharge"),




    ;

    private final Integer code;
    private final String message;

}
