package com.iaminca.web.controller.base;

import com.iaminca.common.Constants;
import com.iaminca.common.ErrorCode;
import com.iaminca.exception.BusinessException;
import com.iaminca.handler.UserBalanceHandler;
import com.iaminca.handler.UserKeyHandler;
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
    private UserKeyHandler userKeyHandler;
    @Resource
    private UserBalanceHandler userBalanceHandler;
    @Resource
    private RedisTemplate redisTemplateIntegerValue;


    public Long getUserID(String gptKey){
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
