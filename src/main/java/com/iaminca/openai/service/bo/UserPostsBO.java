package com.iaminca.openai.service.bo;

import lombok.Data;

import java.util.Date;

/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-06-02 01:03:34
 */
@Data
public class UserPostsBO {
	private static final long serialVersionUID = 1L;

	/***/
	private Long id;
	/***/
	private Long userId;
	/***/
	private Long userTaskId;
	/***/
	private Long postAuthor;
	/***/
	private Date postDate;
	/***/
	private Date postDateGmt;
	/***/
	private String postContent;
	/***/
	private String postTitle;
	/***/
	private String postExcerpt;
	/***/
	private String postStatus;
	/***/
	private String commentStatus;
	/***/
	private String pingStatus;
	/***/
	private String postPassword;
	/***/
	private String postName;
	/***/
	private String postContentFiltered;
	/***/
	private Long postParent;
	/***/
	private String guid;
	/***/
	private Integer menuOrder;
	/***/
	private String postType;
	/***/
	private String postMimeType;
	/***/
	private Integer chatStatus;
	/***/
	private Integer pushStatus;
	/***/
	private Integer repeatNumber;
	/**delete flag: 0 false, 1 true*/
	private Integer delFlag;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;

	private Integer featuredMedia;

	private String categories;
	private String format;
	private String meta;

}
