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
public class ChatRequestBO {
	private static final long serialVersionUID = 1L;
	private Long Id;
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
