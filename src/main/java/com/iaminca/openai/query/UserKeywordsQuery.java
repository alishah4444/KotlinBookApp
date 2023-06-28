package com.iaminca.openai.query;

import com.iaminca.openai.common.model.PageInfo;
import lombok.Data;

import java.util.Date;

/**
 * 实体的查询对象
 * 
 * @author xw
 * @email xw
 * @date 2023-06-02 01:03:34
 */
@Data
public class UserKeywordsQuery extends PageInfo {
	private static final long serialVersionUID = 1L;

	/***/
	private Long id;
	/***/
	private Long userId;
	/***/
	private Long fileName;
	/***/
	private Integer keywordNumber;
	/**Long keywords, seperation with semicolon。 The number of key word is 2000 at least*/
	private String keywords;
	/**delete flag: 0 false, 1 true*/
	private Integer delFlag;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;
}
