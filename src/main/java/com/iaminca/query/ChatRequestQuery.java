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
public class ChatRequestQuery {
	private static final long serialVersionUID = 1L;

	/***/
	private Long id;
	/***/
	private Long userId;
	/***/
	private Long keyId;
	/***/
	private String model;
	/***/
	private Double temperature;
	/***/
	private Double topP;
	/***/
	private Integer n;
	/***/
	private Integer stream;
	/***/
	private String stop;
	/***/
	private Integer maxTokens;
	/***/
	private Double presencePenalty;
	/***/
	private Double frequencyPenalty;
	/***/
	private String logitBias;
	/***/
	private String user;
	/**delete flag: 0 false, 1 true*/
	private Integer delFlag;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;
}
