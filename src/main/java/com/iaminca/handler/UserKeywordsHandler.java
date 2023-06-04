package com.iaminca.handler;

import com.iaminca.common.model.PageListResult;
import com.iaminca.query.UserKeywordsQuery;
import com.iaminca.service.UserKeywordsService;
import com.iaminca.service.bo.UserKeywordsBO;
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
public class UserKeywordsHandler {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate<String,Integer> redisTemplateIntegerValue;
    @Resource
    private UserKeywordsService userKeywordsService;


    public void insert(UserKeywordsBO userKeywordsBO){
        userKeywordsService.add(userKeywordsBO);
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

}
