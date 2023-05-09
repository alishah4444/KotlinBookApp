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
 * @date 2023-05-09 16:08:49
 */
@Data
@Table(name = "user_balance")
public class UserBalanceDO {
	private static final long serialVersionUID = 1L;

	/***/
	@Id
	private Long id;
	/***/
	private Long userId;
	/**unite: cent*/
	private Integer userBalance;
	/**delete flag: 0 false, 1 true*/
	private Integer delFlag;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;
}
