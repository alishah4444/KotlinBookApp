package com.iaminca.dal.dataobject;

import lombok.Data;

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
@Table(name = "chat_response_choices")
public class ChatResponseChoicesDO {
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
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
