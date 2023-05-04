package com.iaminca.dal.dataobject;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:23
 */
@Data
@Table(name = "chat_response")
public class ChatResponseDO {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/***/
	private Long userId;
	/***/
	private String chatResponseId;
	/***/
	private String chatModel;
	/***/
	private String chatObject;
	/***/
	private Long chatCreated;
	/***/
	private Integer chatUsagePromptTokens;
	/***/
	private Integer chatUsageCompletionTokens;
	/***/
	private Integer chatUsageTotalTokens;
}
