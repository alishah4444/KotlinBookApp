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
 * @date 2023-05-16 18:59:03
 */
@Data
@Table(name = "user_apply_gpt4")
public class UserApplyGpt4DO {
	private static final long serialVersionUID = 1L;

	/***/
	@Id
	private Long id;
	/***/
	private Long userId;
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