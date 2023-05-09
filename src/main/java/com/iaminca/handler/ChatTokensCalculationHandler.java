package com.iaminca.handler;

import com.iaminca.common.ErrorCode;
import com.iaminca.exception.BusinessException;
import com.iaminca.utils.RedisKeyUtil;
import com.mysql.cj.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatTokensCalculationHandler {

    private final StringRedisTemplate stringRedisTemplate;
    private final RedisTemplate redisTemplate;

    public void checkGptKey(String gptKey){
        String userIdCache = stringRedisTemplate.opsForValue().get(RedisKeyUtil.getGptKey(gptKey));
        if(StringUtils.isEmptyOrWhitespaceOnly(userIdCache)){
            throw new BusinessException(ErrorCode.GPT_KEY_ERROR);
        }
        String userBalance = stringRedisTemplate.opsForValue().get(RedisKeyUtil.getUserBalance(Long.valueOf(userIdCache)));
        if(Long.valueOf(userBalance) <= 0){
            throw new BusinessException(ErrorCode.GPT_BALANCE_ERROR);
        }
    }

    public void reduceGptTokens(Long userId,long tokensNumber){
        redisTemplate.opsForValue().decrement(RedisKeyUtil.getUserBalance(userId),tokensNumber);
    }

}
