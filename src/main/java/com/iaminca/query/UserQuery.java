package com.iaminca.query;

import lombok.Data;


/**
 * 实体的查询对象
 * 
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:22
 */
@Data
public class UserQuery{
	private static final long serialVersionUID = 1L;

	private Long id;
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
	/***/
	private Integer delFlag;
}
