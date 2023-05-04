package com.iaminca.service.bo;

import lombok.Data;


/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:23
 */
@Data
public class ChatResponseChoicesBO{
	private static final long serialVersionUID = 1L;
	private Long Id;
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
