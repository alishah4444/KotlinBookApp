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
 * @date 2023-05-04 09:54:43
 */
@Data
@Table(name = "chat_request_message")
public class ChatRequestMessageDO {
	private static final long serialVersionUID = 1L;

	/***/
	@Id
	private Long id;
	/***/
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
