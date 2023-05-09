package com.iaminca.service.bo;

import lombok.Data;

import java.util.Date;


/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-09 16:08:49
 */
@Data
public class UserBalanceBO {
	private static final long serialVersionUID = 1L;

	/***/
	private Long id;
	/***/
	private Long userId;
	/**unite: tokens*/
	private Integer userBalance;
	/**delete flag: 0 false, 1 true*/
	private Integer delFlag;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;
}
