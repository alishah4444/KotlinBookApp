package com.iaminca.common;

import com.iaminca.exception.ErrorCodeService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode implements ErrorCodeService {
	SUCCESS(200, "成功"),
	UNKNOW_ERROR(9999, "服务器繁忙，请稍后再试！"),
	PARAM_IS_ERROR(10000, "参数有误"),
	//User Model
	USER_VERIFICATION_CODE_ERROR(11000, "验证码有误"),
	USER_PHONE_ERROR(11001, "用户不存在"),
	USER_PHONE_PASSWORD_ERROR(11002, "账号或密码有误"),

	//GPT Model
	GPT_KEY_ERROR(12000, "GPT key 有误"),
	GPT_BALANCE_ERROR(12001, "余额不足"),




	//权限模块 15500
	NO_AUTH(55555, "登录过期或超时，请重新登录！"),
	SYS_TEM_ERROR(50000, "系统错误"),
	;



	private final int code;
	private final String message;

}
