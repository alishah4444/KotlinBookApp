package com.iaminca.service.bo;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-04 09:54:43
 */
@Data
public class ChatRequestBO {
	private static final long serialVersionUID = 1L;

	/***/
	private Long id;
	private List<ChatRequestMessageBO> messages;
	/** User Id*/
	private Long userId;
	/** User Key ID*/
	private Long keyId;
	private String recordId;
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
	private Boolean stream;
	/***/
	private List<String> stop;
	/***/
	private Integer maxTokens;
	/***/
	private Double presencePenalty;
	/***/
	private Double frequencyPenalty;
	/***/
	private Map<String, Integer> logitBias;
	/***/
	private String user;
	/**delete flag: 0 false, 1 true*/
	private Integer delFlag;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;
}
