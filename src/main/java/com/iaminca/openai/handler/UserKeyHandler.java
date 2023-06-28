package com.iaminca.openai.handler;

import com.google.common.collect.Lists;
import com.iaminca.openai.common.Constants;
import com.iaminca.openai.common.DelFlagEnum;
import com.iaminca.openai.common.ErrorCode;
import com.iaminca.openai.common.model.PageListResult;
import com.iaminca.openai.exception.BusinessException;
import com.iaminca.openai.utils.KeyUtil;
import com.iaminca.openai.query.UserKeyQuery;
import com.iaminca.openai.service.UserKeyService;
import com.iaminca.openai.service.bo.UserKeyBO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:22
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class UserKeyHandler {

    private static final Integer CHAT_LIMITATION = 2;
    private static final Integer LENGTH_LIMITATION = 200;

    @Resource
    private UserKeyService userKeyService;

    public String addUserKey(UserKeyBO userKeyBO){
        log.info("Add UserKeyBO: {}", Constants.GSON.toJson(userKeyBO));
        if(ObjectUtils.isEmpty(userKeyBO) || ObjectUtils.isEmpty(userKeyBO.getUserId())){
            log.info("No User ID");
           throw new BusinessException(ErrorCode.PARAM_IS_ERROR);
        }
        if(StringUtils.isEmpty(userKeyBO.getName())){
            userKeyBO.setName(String.valueOf(System.currentTimeMillis()));
        }
        userKeyBO.setUserKey("sk-"+ KeyUtil.openaiKey(48));
        userKeyBO.setUserChatLimitation(CHAT_LIMITATION);
        userKeyBO.setUserLengthLimitation(LENGTH_LIMITATION);
        userKeyService.add(userKeyBO);
        return userKeyBO.getUserKey();
    }

    public UserKeyBO findUserKey(UserKeyQuery query){
        List<UserKeyBO> list = userKeyService.findList(query);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }

    public UserKeyBO findUserKeyById(Long id){
        UserKeyQuery query = new UserKeyQuery();
        query.setId(id);
        List<UserKeyBO> list = userKeyService.findList(query);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }

    public void deleteUserKey(Long id){
        UserKeyBO userKeyBO = new UserKeyBO();
        userKeyBO.setId(id);
        userKeyBO.setDelFlag(DelFlagEnum.DEL.getCode());
        userKeyService.update(userKeyBO);
    }
    public void updateById(UserKeyBO userKeyBO){
        log.info("updateById  userKeyBO : {}",Constants.GSON.toJson(userKeyBO));
        if(ObjectUtils.isEmpty(userKeyBO) || ObjectUtils.isEmpty(userKeyBO.getId())
                || ObjectUtils.isEmpty(userKeyBO.getUserId())){
            throw new BusinessException(ErrorCode.PARAM_IS_ERROR);
        }
        UserKeyQuery query = new UserKeyQuery();
        query.setUserId(userKeyBO.getUserId());
        query.setId(userKeyBO.getId());
        List<UserKeyBO> list = userKeyService.findList(query);
        if(ObjectUtils.isEmpty(list)){
            throw new BusinessException(ErrorCode.DATA_IS_EMPTY_ERROR);
        }
        UserKeyBO userKeyUpdateBO = new UserKeyBO();
        userKeyUpdateBO.setId(userKeyBO.getId());
        userKeyUpdateBO.setName(userKeyBO.getName());
        userKeyService.update(userKeyUpdateBO);
    }


    public List<UserKeyBO> findUserKeyList(UserKeyQuery query){
        if(ObjectUtils.isEmpty(query) || ObjectUtils.isEmpty(query.getUserId())){
            return Lists.newArrayList();
        }
        return userKeyService.findList(query);
    }

    public PageListResult<UserKeyBO> findUserKeyPage(UserKeyQuery query){
        if(ObjectUtils.isEmpty(query) || ObjectUtils.isEmpty(query.getUserId())){
            return new PageListResult();
        }
        return userKeyService.findPage(query);
    }
}
