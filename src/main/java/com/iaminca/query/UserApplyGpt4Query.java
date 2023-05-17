package com.iaminca.query;

import com.iaminca.common.model.PageInfo;
import lombok.Data;

import java.util.Date;


/**
 * 实体的查询对象
 * 
 * @author xw
 * @email xw
 * @date 2023-05-16 18:59:03
 */
@Data
public class UserApplyGpt4Query extends PageInfo {
	private static final long serialVersionUID = 1L;

	/***/
	private Long id;
	/***/
	private String userName;
	/***/
	private String userEmail;
	/***/
	private String companyName;
	/***/
	private String applyReason;
	/***/
	private String ideaDetails;
	/**delete flag: 0 false, 1 true*/
	private Integer delFlag;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;
}