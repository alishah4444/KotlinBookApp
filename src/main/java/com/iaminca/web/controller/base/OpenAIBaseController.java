package com.iaminca.web.controller.base;

import com.iaminca.common.ErrorCode;
import com.iaminca.exception.BusinessException;
import com.iaminca.handler.UserBalanceHandler;
import com.iaminca.handler.UserKeyHandler;
import com.iaminca.query.UserBalanceQuery;
import com.iaminca.query.UserKeyQuery;
import com.iaminca.service.bo.UserBalanceBO;
import com.iaminca.service.bo.UserKeyBO;
import com.iaminca.utils.RedisKeyUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

public class OpenAIBaseController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private UserKeyHandler userKeyHandler;
    @Resource
    private UserBalanceHandler userBalanceHandler;

    public Long getUserID(String gptKey){
        if(StringUtils.isEmpty(gptKey)){
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        String gptKeyUserID = stringRedisTemplate.opsForValue().get(RedisKeyUtil.getGptKey(gptKey));
        if(StringUtils.isEmpty(gptKeyUserID)){
            UserKeyQuery query = new UserKeyQuery();
            query.setUserKey(gptKey);
            UserKeyBO userKey = userKeyHandler.findUserKey(query);
            if(ObjectUtils.isEmpty(userKey)){
                throw new BusinessException(ErrorCode.GPT_KEY_ERROR);
            }
            UserBalanceQuery balanceQuery = new UserBalanceQuery();
            balanceQuery.setUserId(userKey.getUserId());
            UserBalanceBO userBalance = userBalanceHandler.findUserBalance(balanceQuery);
            stringRedisTemplate.opsForValue().set(RedisKeyUtil.getGptKey(gptKey), String.valueOf(userKey.getUserId()));
            redisTemplate.opsForValue().set(RedisKeyUtil.getUserBalance(userKey.getUserId()),userBalance.getUserBalance());
        }
        return Long.getLong(gptKeyUserID);
    }

}
