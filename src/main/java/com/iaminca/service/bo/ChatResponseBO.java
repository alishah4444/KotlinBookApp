package com.iaminca.service.bo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:23
 */
@Data
public class ChatResponseBO{
	private static final long serialVersionUID = 1L;

	/***/
	private Long id;
	/***/
	private Long userId;
	private Long keyId;
	/***/
	private String chatResponseId;
	/***/
	private String model;
	/***/
	private String object;
	/***/
	private Long created;
	/***/
	private Integer usagePromptTokens;
	/***/
	private Integer usageCompletionTokens;
	/***/
	private Integer usageTotalTokens;
	/**delete flag: 0 false, 1 true*/
	private Integer delFlag;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;
}
