package com.iaminca.dal.dataobject;

import lombok.Data;


/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:23
 */
@Data
public class ChatResponseDO {
	private static final long serialVersionUID = 1L;

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
