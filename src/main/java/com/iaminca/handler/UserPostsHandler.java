package com.iaminca.handler;

import com.iaminca.query.UserPostsQuery;
import com.iaminca.service.UserPostsService;
import com.iaminca.service.bo.UserPostsBO;
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
public class UserPostsHandler {

    @Resource
    private UserPostsService userPostsService;


    public void insert(UserPostsBO UserPostsBO){
        userPostsService.add(UserPostsBO);
    }

    public void updateById(UserPostsBO UserPostsBO){
        userPostsService.update(UserPostsBO);
    }

    public List<UserPostsBO> findList(UserPostsQuery query){
        List<UserPostsBO> list = userPostsService.findList(query);
        return list;
    }


}
