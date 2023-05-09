package com.iaminca.dal.dataobject;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-04 09:54:43
 */
@Data
@Table(name = "chat_request")
public class ChatRequestDO {
	private static final long serialVersionUID = 1L;

	/***/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/** User Id*/
	private Long userId;
	/** User Key ID*/
	private Long keyId;
	private String gptKey;
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
