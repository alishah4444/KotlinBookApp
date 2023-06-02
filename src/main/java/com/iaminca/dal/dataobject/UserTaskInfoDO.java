package com.iaminca.dal.dataobject;

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
public class UserTaskInfoDO {
	private static final long serialVersionUID = 1L;

	/***/
	private Long id;
	/***/
	private Long userId;
	/***/
	private Long apiKeyId;
	/***/
	private String cron;
	/***/
	private String pushUrl;
	/***/
	private String authUsername;
	/***/
	private String authPassword;
	/**The max length of content*/
	private Integer maxLength;
	/**the template for key words sentence, will replace the holdpplace*/
	private String completionTemplate;
	/**delete flag: 0 false, 1 true*/
	private Integer delFlag;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;
}
