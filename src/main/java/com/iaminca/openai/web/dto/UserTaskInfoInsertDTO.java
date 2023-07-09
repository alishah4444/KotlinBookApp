package com.iaminca.openai.web.dto;

import lombok.Data;

/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-06-02 01:03:34
 */
@Data
public class UserTaskInfoInsertDTO {
	private static final long serialVersionUID = 1L;


	/***/
	private Long userId;
	/***/
	private Long userKeywordsId;

	/***/
	private Integer year;
	/***/
	private Integer month;
	/***/
	private Integer day;
	/***/
	private Integer hour;
	/***/
	private Integer minute;
	/***/
	private Integer second;


}
