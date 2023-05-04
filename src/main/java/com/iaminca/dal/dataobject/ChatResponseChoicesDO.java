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
public class ChatResponseChoicesDO {
	private static final long serialVersionUID = 1L;

	/***/
	private String chatResponseId;
	/***/
	private Integer choicesIndex;
	/***/
	private String choicesMessageRole;
	/***/
	private String choicesMessageContent;
	/***/
	private String choicesMessageFinishReason;
}
