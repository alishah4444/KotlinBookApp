package com.iaminca.dal.dataobject;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-06-02 01:03:34
 */
@Data
@Table(name="user_task_info")
public class UserTaskInfoDO {
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
