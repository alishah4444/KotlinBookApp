package com.iaminca.handler;

import com.iaminca.query.UserTaskInfoQuery;
import com.iaminca.service.UserTaskInfoService;
import com.iaminca.service.bo.UserTaskInfoBO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
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

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate<String,Integer> redisTemplateIntegerValue;
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
