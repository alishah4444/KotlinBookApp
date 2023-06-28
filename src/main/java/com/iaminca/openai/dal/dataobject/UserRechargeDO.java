package com.iaminca.openai.dal.dataobject;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-16 11:06:12
 */
@Data
@Table(name = "user_recharge")
public class UserRechargeDO {
	private static final long serialVersionUID = 1L;

	/***/
	@Id
	private Long id;
	/***/
	private Long userId;
	/**unite: cent*/
	private Integer rechargeMoney;
	/***/
	private String filePath;
	/**0 waiting, 1 true,9 false*/
	private Integer rechargeStatus;
	/**delete flag: 0 false, 1 true*/
	private Integer delFlag;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;
}
