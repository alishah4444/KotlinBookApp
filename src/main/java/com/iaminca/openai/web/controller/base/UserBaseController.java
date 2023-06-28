package com.iaminca.openai.web.controller.base;

import com.iaminca.openai.common.Constants;
import com.iaminca.openai.common.ErrorCode;
import com.iaminca.openai.exception.BusinessException;
import com.iaminca.openai.handler.UserBalanceHandler;
import com.iaminca.openai.handler.UserKeyHandler;
import com.iaminca.openai.service.bo.UserBO;
import com.iaminca.openai.service.bo.UserBalanceBO;
import com.iaminca.openai.utils.RedisKeyUtil;
import com.iaminca.openai.query.UserKeyQuery;
import com.iaminca.openai.service.bo.UserKeyBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * Created by chenjin on 18/4/21.
 */
@Slf4j
public class UserBaseController {
	@Resource
	private StringRedisTemplate stringRedisTemplate;
	@Resource
	private UserKeyHandler userKeyHandler;
	@Resource
	private UserBalanceHandler userBalanceHandler;
	@Resource
	private RedisTemplate redisTemplateIntegerValue;

	public Long getUserID(String token){
		if(StringUtils.isEmpty(token)){
			throw new BusinessException(ErrorCode.NO_AUTH);
		}
		String userRedisCache = RedisKeyUtil.userInfoKey(token);

		String userInfo = stringRedisTemplate.opsForValue().get(userRedisCache);
		if(ObjectUtils.isEmpty(userInfo)){
			throw new BusinessException(ErrorCode.NO_AUTH);
		}
		UserBO userBO = Constants.GSON.fromJson(userInfo, UserBO.class);
		return userBO.getId();
	}


	public UserBO getUser(String token){
		if(StringUtils.isEmpty(token)){
			throw new BusinessException(ErrorCode.NO_AUTH);
		}
		String userRedisCache = RedisKeyUtil.userInfoKey(token);

		String userInfo = stringRedisTemplate.opsForValue().get(userRedisCache);
		if(ObjectUtils.isEmpty(userInfo)){
			throw new BusinessException(ErrorCode.NO_AUTH);
		}
		UserBO userBO = Constants.GSON.fromJson(userInfo, UserBO.class);
		return userBO;
	}

	public Long getUserIDByGptKey(String gptKey){
		if(StringUtils.isEmpty(gptKey)){
			throw new BusinessException(ErrorCode.NO_AUTH);
		}
		String gptKeyUserID = stringRedisTemplate.opsForValue().get(RedisKeyUtil.getGptKey(gptKey));
		UserKeyBO userKeyBO = Constants.GSON.fromJson(gptKeyUserID, UserKeyBO.class);
		if(ObjectUtils.isEmpty(userKeyBO) || StringUtils.isEmpty(userKeyBO.getUserId())){
			UserKeyQuery query = new UserKeyQuery();
			query.setUserKey(gptKey);
			userKeyBO = userKeyHandler.findUserKey(query);
			if(ObjectUtils.isEmpty(userKeyBO)){
				throw new BusinessException(ErrorCode.GPT_KEY_ERROR);
			}
			UserBalanceBO userBalance = userBalanceHandler.findUserBalance(userKeyBO.getUserId());
			stringRedisTemplate.opsForValue().set(RedisKeyUtil.getGptKey(gptKey), Constants.GSON.toJson(userKeyBO));
			String userBalanceRedisKey = RedisKeyUtil.getUserBalance(userKeyBO.getUserId());
//            stringRedisTemplate.opsForValue().set(userBalanceRedisKey,String.valueOf(userBalance.getUserBalance()));
			redisTemplateIntegerValue.opsForValue().set(userBalanceRedisKey,userBalance.getUserBalance());
		}
		return userKeyBO.getUserId();
	}

}
