package com.iaminca.service.bo;

import lombok.Data;


/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:22
 */
@Data
public class UserBalanceCacheBO {
	private static final long serialVersionUID = 1L;

	/***/
	private Long userId;

	private Long userTokensBalance;

}
