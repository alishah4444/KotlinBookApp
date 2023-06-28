package com.iaminca.openai.dal.dataobject;

import lombok.Data;

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
@Table(name = "chat_response_choices")
public class ChatResponseChoicesDO {
	private static final long serialVersionUID = 1L;

	/***/
	@Id
	private Long id;
	/***/
	private Long responseId;
	/***/
	private Integer chatIndex;
	/***/
	private String messageRole;
	/***/
	private String messageContent;
	/***/
	private String messageFinishReason;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;
}
