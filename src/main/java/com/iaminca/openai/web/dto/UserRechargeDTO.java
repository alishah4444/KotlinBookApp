package com.iaminca.openai.web.dto;

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
public class UserRechargeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	/***/
	private Long id;
	/***/
	private Long userId;
	/***/
	private Double rechargeMoney;
	/***/
	private String filePath;
	/**delete flag: 0 false, 1 true*/
	private Integer delFlag;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;
	/**0 waiting, 1 true,9 false*/
	private Integer rechargeStatus;

}
