package com.iaminca.service.bo;

import lombok.Data;

import java.util.Date;

/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-06-02 01:03:34
 */
@Data
public class UserTaskInfoBO {
	private static final long serialVersionUID = 1L;

	/***/
	private Long id;
	/***/
	private Long userId;


	private Integer taskStatus;
	/**How many times process the task*/
	private Integer processNumber;
	/***/
	private String cron;
	/**delete flag: 0 false, 1 true*/
	private Integer delFlag;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;
}
