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
@Table(name = "chat_request")
public class ChatRequestDO {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	/***/
	private Long userId;
	/***/
	private String chatModel;
	/***/
	private String chatContent;
	/***/
	private Integer chatMaxToken;
	/***/
	private Integer chatMaxNumber;
}
