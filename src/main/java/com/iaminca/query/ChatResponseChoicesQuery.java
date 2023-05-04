package com.iaminca.query;

import lombok.Data;

import java.util.Date;


/**
 * 实体的查询对象
 * 
 * @author xw
 * @email xw
 * @date 2023-05-04 16:59:37
 */
@Data
public class ChatResponseChoicesQuery {
	private static final long serialVersionUID = 1L;

	/***/
	private Long id;
	/***/
	private Long responseId;
	/***/
	private Integer index;
	/***/
	private String messageRole;
	/***/
	private String messageContent;
	/***/
	private String messageFinishReason;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;
}
