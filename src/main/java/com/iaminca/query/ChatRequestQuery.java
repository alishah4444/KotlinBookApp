package com.iaminca.query;

import lombok.Data;


/**
 * 实体的查询对象
 * 
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:23
 */
@Data
public class ChatRequestQuery {
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
