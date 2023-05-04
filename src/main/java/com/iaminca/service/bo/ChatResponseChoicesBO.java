package com.iaminca.service.bo;

import lombok.Data;

import java.util.Date;


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

	/***/
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
