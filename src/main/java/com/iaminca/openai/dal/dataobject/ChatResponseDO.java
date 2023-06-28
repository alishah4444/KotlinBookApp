package com.iaminca.openai.dal.dataobject;

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
 * @date 2023-05-04 16:59:37
 */
@Data
@Table(name = "chat_response")
public class ChatResponseDO {
	private static final long serialVersionUID = 1L;

	/***/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/***/
	private Long userId;
	private Long keyId;
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
