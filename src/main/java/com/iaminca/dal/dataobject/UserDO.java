package com.iaminca.dal.dataobject;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:22
 */
@Data
@Table(name = "user")
public class UserDO {
	private static final long serialVersionUID = 1L;
	@Id
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
}
