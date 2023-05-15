package com.iaminca.web.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-04 17:34:01
 */
@Data
public class UserKeyDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	/***/
	private Long id;
	/***/
	private Long userId;
	/***/
	private String userKey;
	/***/
	private Integer userChatLimitation;
	/***/
	private Integer userLengthLimitation;
	/**delete flag: 0 false, 1 true*/
	private Integer delFlag;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;
	/***/
	private String name;
}
