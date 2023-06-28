//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.iaminca.openai.common;

/**
 * @author 优行科技
 * @param <T>
 */
public class ResultModel<T> {
	public static final int REST_SUCCESS_CODE = 200;

	/**
	 * 消息
	 */
	private String message = "success";

	/**
	 * 编码
	 */
	private int code = 200;

	/**
	 * 响应实体
	 */
	private T data;

	public ResultModel() {
	}

	public ResultModel(T data) {
		this.data = data;
	}

	public ResultModel(int code, String message) {
		this.code = code;
		this.message = message;
	}

//	public ResultModel(ErrorCode error, Object... arguments) {
//		this.code = error.getCode();
//		this.message = MessageFormat.format(error.getMessage(), arguments);
//	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}

//	public void setError(ErrorCode error) {
//		this.code = error.getCode();
//		this.message = error.getMessage();
//	}
//
//	public void setError(ErrorCode error, Object... arguments) {
//		this.code = error.getCode();
//		this.message = MessageFormat.format(error.getMessage(), arguments);
//	}

	public boolean isSuccess() {
		return this.code == 200;
	}

}
