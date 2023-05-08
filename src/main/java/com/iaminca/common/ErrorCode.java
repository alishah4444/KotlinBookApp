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
	//权限模块 15500

	NO_AUTH(55555, "权限不足，请联系管理员"),
	SYS_TEM_ERROR(50000, "系统错误"),
	;



	private final int code;
	private final String message;

}
