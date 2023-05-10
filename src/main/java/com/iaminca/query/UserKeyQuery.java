package com.iaminca.query;

import com.iaminca.common.model.PageInfo;
import lombok.Data;

import java.util.Date;


/**
 * 实体的查询对象
 * 
 * @author xw
 * @email xw
 * @date 2023-05-04 17:34:01
 */
@Data
public class UserKeyQuery extends PageInfo {
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
}
