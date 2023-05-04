package com.iaminca.web.dto;

import lombok.Data;

import java.util.Date;


/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:22
 */
@Data
public class UserDTO {
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
	/**delete flag: 0 false, 1 true*/
	private Integer delFlag;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;
}
