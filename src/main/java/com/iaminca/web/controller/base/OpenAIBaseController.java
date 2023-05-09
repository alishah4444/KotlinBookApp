package com.iaminca.web.controller.base;

import com.iaminca.common.ErrorCode;
import com.iaminca.exception.BusinessException;
import com.iaminca.utils.RedisKeyUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

public class OpenAIBaseController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public Long getUserID(String gptKey){
        if(StringUtils.isEmpty(gptKey)){
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        String gptKeyUserID = stringRedisTemplate.opsForValue().get(RedisKeyUtil.getGptKey(gptKey));
//        String balance = stringRedisTemplate.opsForValue().get(RedisKeyUtil.getUserBalance(gptKeyUserID));
//        UserBalanceBO userBalanceBO = Constants.GSON.fromJson(balance, UserBalanceBO.class);
        return (Long)gptKeyUserID;
    }

}
