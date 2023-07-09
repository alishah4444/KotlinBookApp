package com.iaminca.openai.handler;

import com.iaminca.openai.common.DelFlagEnum;
import com.iaminca.openai.common.ErrorCode;
import com.iaminca.openai.common.model.PageListResult;
import com.iaminca.openai.exception.BusinessException;
import com.iaminca.openai.query.UserTaskInfoQuery;
import com.iaminca.openai.service.UserTaskInfoService;
import com.iaminca.openai.service.bo.UserTaskInfoBO;
import com.iaminca.openai.service.bo.UserTaskInfoInsertBO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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
public class UserTasksInfoHandler {

    @Resource
    private UserTaskInfoService userTaskInfoService;


    public void insert(UserTaskInfoInsertBO userTaskInfoInsertBO){
        String cron = "20 * * * * ?";
        // TODO 从参数里解析CRON表达式，并赋值给cron。

        UserTaskInfoBO UserTaskInfoBO = new UserTaskInfoBO();
        UserTaskInfoBO.setProcessNumber(0);
        UserTaskInfoBO.setCron(cron);
        userTaskInfoService.add(UserTaskInfoBO);
    }

    public void updateById(UserTaskInfoBO userTaskInfoBO){
        UserTaskInfoQuery query = new UserTaskInfoQuery();
        query.setId(userTaskInfoBO.getId());
        query.setUserId(userTaskInfoBO.getUserId());
        List<UserTaskInfoBO> taskInfoBOS = findList(query);
        if(CollectionUtils.isEmpty(taskInfoBOS)){
            throw new BusinessException(ErrorCode.DATA_IS_EMPTY_ERROR);
        }
        UserTaskInfoBO updateBO = new UserTaskInfoBO();
        updateBO.setId(userTaskInfoBO.getId());
        updateBO.setUserKeywordsId(userTaskInfoBO.getUserKeywordsId());
        updateBO.setCron(userTaskInfoBO.getCron());
        userTaskInfoService.update(updateBO);
    }


    public void delById(UserTaskInfoBO userTaskInfoBO){
        UserTaskInfoQuery query = new UserTaskInfoQuery();
        query.setId(userTaskInfoBO.getId());
        query.setUserId(userTaskInfoBO.getUserId());
        List<UserTaskInfoBO> taskInfoBOS = findList(query);
        if(CollectionUtils.isEmpty(taskInfoBOS)){
            throw new BusinessException(ErrorCode.DATA_IS_EMPTY_ERROR);
        }
        UserTaskInfoBO updateBO = new UserTaskInfoBO();
        updateBO.setId(userTaskInfoBO.getId());
        updateBO.setDelFlag(DelFlagEnum.DEL.getCode());
        userTaskInfoService.update(updateBO);
    }
    public List<UserTaskInfoBO> findList(UserTaskInfoQuery query){
        List<UserTaskInfoBO> list = userTaskInfoService.findList(query);
        return list;
    }
    public UserTaskInfoBO findOne(UserTaskInfoQuery query){
        UserTaskInfoBO infoServiceOne = userTaskInfoService.findOne(query);
        return infoServiceOne;
    }
    public PageListResult<UserTaskInfoBO> findPage(UserTaskInfoQuery query){
        PageListResult<UserTaskInfoBO> list = userTaskInfoService.findPage(query);
        return list;
    }


}
