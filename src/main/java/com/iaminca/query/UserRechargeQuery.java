package com.iaminca.query;

import com.iaminca.common.model.PageInfo;
import lombok.Data;

import java.util.Date;


/**
 * 实体的查询对象
 * 
 * @author xw
 * @email xw
 * @date 2023-05-16 11:06:12
 */
@Data
public class UserRechargeQuery extends PageInfo {
	private static final long serialVersionUID = 1L;

	/***/
	private Long id;
	/***/
	private Long userId;
	/**unite: cent*/
	private Integer moneyNumber;
	/**0 waiting, 1 true,9 false*/
	private Integer rechargeStatus;
	/**delete flag: 0 false, 1 true*/
	private Integer delFlag;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;
}
