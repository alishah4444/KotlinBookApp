package com.iaminca.handler;

import com.iaminca.common.Constants;
import com.iaminca.common.ErrorCode;
import com.iaminca.exception.BusinessException;
import com.iaminca.service.bo.ChatRequestBO;
import com.iaminca.service.bo.ChatResponseBO;
import com.iaminca.service.bo.UserKeyBO;
import com.iaminca.utils.RedisKeyUtil;
import com.mysql.cj.util.StringUtils;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionChunk;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ChatTokensCalculationHandler {

    private final StringRedisTemplate stringRedisTemplate;
    private final RedisTemplate redisTemplate;
    private final ChatResponseHandler chatResponseHandler;
    private final RedisTemplate<String, Integer> redisTemplateIntegerValue;

    public UserKeyBO checkGptKey(String gptKey){
        String userIdCache = stringRedisTemplate.opsForValue().get(RedisKeyUtil.getGptKey(gptKey));
        UserKeyBO userKeyBO = Constants.GSON.fromJson(userIdCache, UserKeyBO.class);
        if(ObjectUtils.isEmpty(userKeyBO) || ObjectUtils.isEmpty(userKeyBO.getId())){
            throw new BusinessException(ErrorCode.GPT_KEY_ERROR);
        }
        Integer userBalance = redisTemplateIntegerValue.opsForValue().get(RedisKeyUtil.getUserBalance(userKeyBO.getUserId()));
        if(userBalance <= 0){
            throw new BusinessException(ErrorCode.GPT_BALANCE_ERROR);
        }
        return userKeyBO;
    }

    public void reduceGptTokens(Long userId,long tokensNumber){
        redisTemplateIntegerValue.opsForValue().decrement(RedisKeyUtil.getUserBalance(userId),tokensNumber);
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



        ChatResponseBO chatResponseBO = new ChatResponseBO();
        chatResponseBO.setUserId(chatRequestBO.getUserId());
        chatResponseBO.setKeyId(1L);
        chatResponseBO.setChatResponseId(list.get(0).getId());
        chatResponseBO.setModel(list.get(0).getModel());
        chatResponseBO.setCreated(list.get(0).getCreated());
        chatResponseBO.setObject(list.get(0).getObject());
        chatRequestBO.getMessages().get(0).getContent();//TODO Calculate the length of sentences.
//        chatResponseBO.setUsageCompletionTokens(new Long(chatCompletion.getUsage().getCompletionTokens()).intValue());
//        chatResponseBO.setUsagePromptTokens(new Long(chatCompletion.getUsage().getPromptTokens()).intValue());
//        chatResponseBO.setUsageTotalTokens(new Long(chatCompletion.getUsage().getTotalTokens()).intValue());
//
//
//        chatResponseHandler.addChatRequest(chatResponseBO,chatResponseChoicesList);
//        //Reduce the user's tokens.
//        this.reduceGptTokens(request.getUserId(),chatCompletion.getUsage().getTotalTokens());
    }

}
