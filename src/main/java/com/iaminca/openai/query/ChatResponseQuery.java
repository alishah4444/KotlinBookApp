package com.iaminca.openai.query;

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
public class ChatResponseQuery{
	private static final long serialVersionUID = 1L;

	/***/
	private Long id;
	/***/
	private Long userId;
	private String recordId;
	/***/
	private String chatResponseId;
	/***/
	private String model;
	/***/
	private String object;
	/***/
	private Long created;
	/***/
	private Integer usagePromptTokens;
	/***/
	private Integer usageCompletionTokens;
	/***/
	private Integer usageTotalTokens;
	/**delete flag: 0 false, 1 true*/
	private Integer delFlag;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;
}
