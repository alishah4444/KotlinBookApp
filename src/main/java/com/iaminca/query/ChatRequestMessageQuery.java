package com.iaminca.query;

import lombok.Data;

import java.util.Date;


/**
 * 实体的查询对象
 * 
 * @author xw
 * @email xw
 * @date 2023-05-04 09:54:43
 */
@Data
public class ChatRequestMessageQuery {
	private static final long serialVersionUID = 1L;

	/***/
	private Long id;
	/***/
	private Long chatRequestId;
	/***/
	private String role;
	/***/
	private String content;
	/**delete flag: 0 false, 1 true*/
	private Integer delFlag;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;
}
