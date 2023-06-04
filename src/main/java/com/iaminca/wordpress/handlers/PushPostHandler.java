package com.iaminca.wordpress.handlers;

import com.iaminca.common.Constants;
import com.iaminca.common.ErrorCode;
import com.iaminca.exception.BusinessException;
import com.iaminca.handler.*;
import com.iaminca.query.UserKeyQuery;
import com.iaminca.query.UserKeywordsQuery;
import com.iaminca.query.UserTaskInfoQuery;
import com.iaminca.service.bo.*;
import com.iaminca.utils.RedisKeyUtil;
import com.iaminca.wordpress.beans.RequestGPTBO;
import com.iaminca.wordpress.handlers.base.PushPostService;
import com.iaminca.wordpress.push.PushPosts;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class PushPostHandler{
    @Resource
    private UserKeywordsHandler userKeywordsHandler;
    @Resource
    private UserTasksInfoHandler userTasksInfoHandler;
    @Resource
    private ChatHandler chatHandler;
    @Resource
    private PushPosts pushPosts;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private UserKeyHandler userKeyHandler;
    @Resource
    private UserBalanceHandler userBalanceHandler;
    @Resource
    private RedisTemplate redisTemplateIntegerValue;

    /**
     * Just call this method
     * @param taskId
     */
    public void hookExecute(Long taskId){
        Long userKeywordsId = this.pushProcess(taskId).getUserKeywordsId();
        UserKeywordsBO userKeywords = this.getUserKeywords(userKeywordsId);

        this.checkGptKey(userKeywords.getUserKey());
        String componentKeywords = this.componentKeywords(userKeywords);

        RequestGPTBO requestGPTBO = new RequestGPTBO();
        requestGPTBO.setUserId(userKeywords.getUserId());
        requestGPTBO.setGptKey(userKeywords.getUserKey());
        requestGPTBO.setKeySentence(componentKeywords);
        ChatCompletionResult chatCompletionResult = this.requestGPT(requestGPTBO);
        //Push post
        pushPosts.executePush(userKeywords.getPushUrl(),
                userKeywords.getAuthUsername(),
                userKeywords.getAuthPassword(),
                componentKeywords,
                chatCompletionResult.getChoices().get(0).getMessage().getContent(),
                null
                );
    }


    public ChatCompletionResult requestGPT(RequestGPTBO requestGPTBO){
        ChatRequestBO request = new ChatRequestBO();
        request.setModel(Constants.GPT_CHAT_MODEL);
        request.setN(1);
        request.setMaxTokens(200);
        request.setUserId(requestGPTBO.getUserId());
        request.setGptKey(requestGPTBO.getGptKey());
        List<ChatRequestMessageBO> messageList = new ArrayList();
        ChatRequestMessageBO chatMessage = new ChatRequestMessageBO();
        chatMessage.setRole("user");
        chatMessage.setContent(requestGPTBO.getKeySentence());
        messageList.add(chatMessage);
        request.setMessages(messageList);

        return chatHandler.createChatCompletion(request);
    }

    public UserTaskInfoBO pushProcess(Long taskId){
        UserTaskInfoQuery query = new UserTaskInfoQuery();
        query.setId(taskId);
        UserTaskInfoBO one = userTasksInfoHandler.findOne(query);
        log.info("DONE.{}", Constants.GSON.toJson(one));
        return one;
    }

    public String componentKeywords(UserKeywordsBO keywordsOne){
        log.info("componentKeywords : {}",Constants.GSON.toJson(keywordsOne));

        String[] splitKeywordsList = keywordsOne.getKeywords().split("\\|",-1);
        if(keywordsOne.getKeywordNumber() == null || keywordsOne.getKeywordNumber()>splitKeywordsList.length -1){
            keywordsOne.setKeywordNumber(0);
        }
        String str = splitKeywordsList[keywordsOne.getKeywordNumber()];
        String completionTemplate = keywordsOne.getCompletionTemplate();

        String contextGPT = MessageFormat.format(completionTemplate, str);

        //Update keywords number
        UserKeywordsBO newKeywordsUpdateBO = new UserKeywordsBO();
        newKeywordsUpdateBO.setId(keywordsOne.getId());
        newKeywordsUpdateBO.setKeywordNumber(keywordsOne.getKeywordNumber()+1);
        userKeywordsHandler.updateById(newKeywordsUpdateBO);

        return contextGPT;
    }

    public UserKeywordsBO getUserKeywords(Long id){
        UserKeywordsQuery queryKeywords = new UserKeywordsQuery();
        queryKeywords.setId(id);
        return userKeywordsHandler.findOne(queryKeywords);
    }


    public Long checkGptKey(String gptKey){
        if(StringUtils.isEmpty(gptKey)){
            throw new BusinessException(ErrorCode.GPT_KEY_CONFIG_ERROR);
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
