package com.iaminca.web.dto;

import lombok.Data;


/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:22
 */
@Data
public class UserLoginDTO {
	private static final long serialVersionUID = 1L;

	/***/
	private String userPhone;
	private String password;

}
