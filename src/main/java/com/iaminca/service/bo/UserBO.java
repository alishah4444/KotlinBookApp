package com.iaminca.service.bo;

import lombok.Data;


/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:22
 */
@Data
public class UserBO{
	private static final long serialVersionUID = 1L;

	/***/
	private String userName;
	/***/
	private String userPhone;
	/***/
	private Integer userChatLimitation;
	/***/
	private Integer userLengthLimitation;
	/**vistor,user,vip,svip*/
	private String userType;
}
