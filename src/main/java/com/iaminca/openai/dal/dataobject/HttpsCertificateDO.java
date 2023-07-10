package com.iaminca.openai.dal.dataobject;

import lombok.Data;
import java.util.Date;

/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-07-10 14:20:08
 */
@Data
public class HttpsCertificateDO {
	private static final long serialVersionUID = 1L;

	/***/
	private Long id;
	/***/
	private Long userId;
	/***/
	private String siteUrl;
	/***/
	private String privateKeyUrl;
	/***/
	private String publicKeyUrl;
	/***/
	private Date expireTime;
	/**status flag: 0 Efficient*/
	private Integer keyStatue;
	/**delete flag: 0 false, 1 true*/
	private Integer delFlag;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;
}
