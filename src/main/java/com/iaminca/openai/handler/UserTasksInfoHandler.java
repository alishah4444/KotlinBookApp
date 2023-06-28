package com.iaminca.openai.handler;

import com.iaminca.openai.query.UserTaskInfoQuery;
import com.iaminca.openai.service.UserTaskInfoService;
import com.iaminca.openai.service.bo.UserTaskInfoBO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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


    public void insert(UserTaskInfoBO userTaskInfoBO){
        userTaskInfoService.add(userTaskInfoBO);
    }

    public void updateById(UserTaskInfoBO userTaskInfoBO){
        userTaskInfoService.update(userTaskInfoBO);
    }

    public List<UserTaskInfoBO> findList(UserTaskInfoQuery query){
        List<UserTaskInfoBO> list = userTaskInfoService.findList(query);
        return list;
    }
    public UserTaskInfoBO findOne(UserTaskInfoQuery query){
        UserTaskInfoBO infoServiceOne = userTaskInfoService.findOne(query);
        return infoServiceOne;
    }


}
