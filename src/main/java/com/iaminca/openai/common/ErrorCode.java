package com.iaminca.openai.common;

import com.iaminca.openai.exception.ErrorCodeService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode implements ErrorCodeService {
	SUCCESS(200, "成功"),
	UNKNOW_ERROR(9999, "服务器繁忙，请稍后再试！"),
	PARAM_IS_ERROR(10000, "参数有误"),
	DATA_IS_EMPTY_ERROR(10001, "操作数据不存在"),
	WEB_HEADER_ERROR(10002, "缺少必要参数或请求权限"),
	//User Model
	USER_VERIFICATION_CODE_ERROR(11000, "验证码有误"),
	USER_PHONE_ERROR(11001, "用户不存在"),
	USER_PHONE_PASSWORD_ERROR(11002, "账号或密码有误"),
	USER_EXIST_ERROR(11003, "用户已经存在，请直接登录"),
	USER_FILE_ERROR(11004, "文件上传异常"),
	USER_FILE_SUFFIX_ERROR(11005, "支持文件类型：jpg,png,jpeg"),

	//GPT Model
	GPT_KEY_ERROR(12000, "GPT key 有误"),
	GPT_BALANCE_ERROR(12001, "余额不足"),
	RECHARGE_BALANCE_ERROR(12002, "金额有误，最小1元"),
	GPT_KEY_CONFIG_ERROR(12003, "GPT key 配置有误"),

	//WordPress Module
	AUTHORIZE_ERROR(13000, " 授权失败！"),



	//权限模块 15500
	NO_AUTH(55555, "登录过期或超时，请重新登录！"),
	SYS_TEM_ERROR(50000, "系统错误"),
	;



	private final int code;
	private final String message;

}