package com.iaminca.openai.handler;

import com.iaminca.openai.common.AuthorizeStatusEnum;
import com.iaminca.openai.common.Constants;
import com.iaminca.openai.common.DelFlagEnum;
import com.iaminca.openai.common.ErrorCode;
import com.iaminca.openai.common.model.PageListResult;
import com.iaminca.openai.exception.BusinessException;
import com.iaminca.openai.query.UserKeywordsQuery;
import com.iaminca.openai.service.UserKeywordsService;
import com.iaminca.openai.service.bo.UserKeyBO;
import com.iaminca.openai.service.bo.UserKeywordsBO;
import com.iaminca.openai.service.bo.WordpressDeleteResponseBean;
import com.iaminca.openai.service.bo.WordpressPostResponseBean;
import com.iaminca.openai.utils.DateUtil;
import com.iaminca.openai.wordpress.beans.WordpressTaskBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
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
public class UserKeywordsHandler {

    @Resource
    private UserKeywordsService userKeywordsService;
    @Resource
    private WordPressHandler wordPressHandler;
    @Resource
    private UserKeyHandler userKeyHandler;


    public void authorizing(UserKeywordsBO userKeywordsBO){
        UserKeywordsBO insertBO = new UserKeywordsBO();
        insertBO.setUserId(userKeywordsBO.getUserId());
        insertBO.setPushUrl(userKeywordsBO.getPushUrl());
        insertBO.setAuthUsername(userKeywordsBO.getAuthUsername());
        insertBO.setAuthPassword(userKeywordsBO.getAuthPassword());
        insertBO.setAuthorizeStatus(AuthorizeStatusEnum.AUTHORIZING.getCode());

        WordpressTaskBean wordpressTaskBean = new WordpressTaskBean();
        wordpressTaskBean.setDate(DateUtil.formDate(new Date(),DateUtil.DATETIME_FORMAT_PATTERN));
        wordpressTaskBean.setDate_gmt(DateUtil.formDate(new Date(),DateUtil.DATETIME_FORMAT_PATTERN));
        wordpressTaskBean.setSlug("aiPost");
        wordpressTaskBean.setStatus("draft");
        wordpressTaskBean.setTitle("AI 自动托管授权");
        wordpressTaskBean.setContent("AI 自动托管授权");
        wordpressTaskBean.setComment_status("closed");
        wordpressTaskBean.setFeatured_media(1);
        wordpressTaskBean.setPing_status("open");
        wordpressTaskBean.setFormat("standard");

        WordpressPostResponseBean pushPostResult = wordPressHandler.pushPost(insertBO.getPushUrl(), insertBO.getAuthUsername(), insertBO.getAuthPassword(), wordpressTaskBean);
        if(pushPostResult == null || ObjectUtils.isEmpty(pushPostResult.getId())){
            throw new BusinessException(ErrorCode.AUTHORIZE_ERROR);
        }
        WordpressDeleteResponseBean delPostResult = wordPressHandler.deletePost(insertBO.getPushUrl(), insertBO.getAuthUsername(), insertBO.getAuthPassword(), pushPostResult.getId());
        if(delPostResult == null || ObjectUtils.isEmpty(pushPostResult.getId())){
            throw new BusinessException(ErrorCode.AUTHORIZE_ERROR);
        }
        userKeywordsService.add(insertBO);
    }


    public void authorizingForPushing(UserKeywordsBO userKeywordsBO){
        UserKeywordsQuery query = new UserKeywordsQuery();
        query.setId(userKeywordsBO.getId());
        query.setUserId(userKeywordsBO.getUserId());
        UserKeywordsBO byId = this.findOne(query);
        if(ObjectUtils.isEmpty(byId)){
            throw new BusinessException(ErrorCode.DATA_IS_EMPTY_ERROR);
        }
        UserKeyBO userKeyById = userKeyHandler.findUserKeyById(userKeywordsBO.getApiKeyId());
        if(ObjectUtils.isEmpty(userKeyById)){
            throw new BusinessException(ErrorCode.GPT_KEY_ERROR);
        }


        UserKeywordsBO insertBO = new UserKeywordsBO();
        insertBO.setId(userKeywordsBO.getId());
        insertBO.setApiKeyId(userKeywordsBO.getApiKeyId());
        insertBO.setUserKey(userKeyById.getUserKey());
        insertBO.setKeywords(userKeywordsBO.getKeywords());
        insertBO.setKeywordNumber(0);
        insertBO.setAuthorizeStatus(AuthorizeStatusEnum.AUTHORIZED_PUSH.getCode());

        userKeywordsService.update(insertBO);
    }


    /**
     * Utilizing ID to Delete the data
     * @param userKeywordsBO
     */
    public void delAuthorizing(UserKeywordsBO userKeywordsBO){
        UserKeywordsQuery query = new UserKeywordsQuery();
        query.setId(userKeywordsBO.getId());
        query.setUserId(userKeywordsBO.getUserId());
        UserKeywordsBO byId = this.findOne(query);
        if(ObjectUtils.isEmpty(byId)){
            throw new BusinessException(ErrorCode.DATA_IS_EMPTY_ERROR);
        }
        UserKeywordsBO insertBO = new UserKeywordsBO();
        insertBO.setId(userKeywordsBO.getId());
        insertBO.setDelFlag(DelFlagEnum.DEL.getCode());
        userKeywordsService.update(insertBO);
    }


    public void updateById(UserKeywordsBO userKeywordsBO){
        userKeywordsService.update(userKeywordsBO);
    }

    public List<UserKeywordsBO> findList(UserKeywordsQuery query){
        List<UserKeywordsBO> list = userKeywordsService.findList(query);
        return list;
    }

    public PageListResult<UserKeywordsBO> findPage(UserKeywordsQuery query){
        PageListResult<UserKeywordsBO> page = userKeywordsService.findPage(query);
        return page;
    }

    public UserKeywordsBO findOne(UserKeywordsQuery query){
        UserKeywordsBO serviceOne = userKeywordsService.findOne(query);
        return serviceOne;
    }
    public UserKeywordsBO findById(Long id){
        if(id == null){
            return null;
        }
        UserKeywordsQuery query = new UserKeywordsQuery();
        query.setId( id);
        UserKeywordsBO serviceOne = this.findOne(query);
        return serviceOne;
    }

}
