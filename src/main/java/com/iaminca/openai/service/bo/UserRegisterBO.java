package com.iaminca.openai.service.bo;

import lombok.Data;


/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:22
 */
@Data
public class UserRegisterBO {
	private static final long serialVersionUID = 1L;

	/***/
	private String userPhone;
	private String verificationCode;
	private String password;

}
