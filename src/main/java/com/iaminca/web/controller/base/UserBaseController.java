package com.iaminca.web.controller.base;

import com.iaminca.common.Constants;
import com.iaminca.common.ErrorCode;
import com.iaminca.exception.BusinessException;
import com.iaminca.service.bo.UserBO;
import com.iaminca.utils.RedisKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * Created by chenjin on 18/4/21.
 */
@Slf4j
public class UserBaseController {
	@Resource
	private StringRedisTemplate stringRedisTemplate;

	public Long getUserID(String token){
		if(StringUtils.isEmpty(token)){
			throw new BusinessException(ErrorCode.NO_AUTH);
		}
		String userInfo = stringRedisTemplate.opsForValue().get(RedisKeyUtil.userInfoKey(token));
		UserBO userBO = Constants.GSON.fromJson(userInfo, UserBO.class);
		return userBO.getId();
	}

}