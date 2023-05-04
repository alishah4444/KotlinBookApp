package com.iaminca.service.bo;

import lombok.Data;

import java.util.Date;


/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-04 09:54:43
 */
@Data
public class ChatRequestMessageBO {
	private static final long serialVersionUID = 1L;

	/***/
	private Long id;
	/**foreign key*/
	private Long chatRequestId;
	/***/
	private String role;
	/***/
	private String content;
	/**delete flag: 0 false, 1 true*/
	private Integer delFlag;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;
}
