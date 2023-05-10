package com.iaminca.handler;

import com.iaminca.common.Constants;
import com.iaminca.common.ErrorCode;
import com.iaminca.exception.BusinessException;
import com.iaminca.service.bo.ChatRequestBO;
import com.iaminca.utils.RedisKeyUtil;
import com.mysql.cj.util.StringUtils;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionChunk;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
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


    public void saveChatCompletionChunk(ChatRequestBO chatRequestBO, List<ChatCompletionChunk> list) {
        log.info(Constants.GSON.toJson(chatRequestBO) );
        log.info(  "********** " );
        log.info(Constants.GSON.toJson(list));
        StringBuffer sb = new StringBuffer();
        for(ChatCompletionChunk chatCompletionChunk: list){
            for(ChatCompletionChoice chatCompletionChoice: chatCompletionChunk.getChoices()){
                String content = chatCompletionChoice.getMessage().getContent();
                if(!StringUtils.isEmptyOrWhitespaceOnly(content) && !"null".equals(content)){
                    sb.append(content);
                }
            }
        }
        log.info("String buffer : {}",sb);
    }

}
