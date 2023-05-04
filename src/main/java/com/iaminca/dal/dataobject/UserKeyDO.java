package com.iaminca.dal.dataobject;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-04 17:34:01
 */
@Data
@Table(name = "user_key")
public class UserKeyDO {
	private static final long serialVersionUID = 1L;

	/***/
	@Id
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
}
