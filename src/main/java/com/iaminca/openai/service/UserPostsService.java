package com.iaminca.openai.service;

import com.iaminca.openai.common.model.PageListResult;
import com.iaminca.openai.query.UserPostsQuery;
import com.iaminca.openai.service.bo.UserPostsBO;

import java.util.List;
/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-06-02 01:03:34
 */
public interface UserPostsService {

    /**
	 * 添加数据
	 * @param
	 * @return
	 */
    int add(UserPostsBO userPostsBO);

    /**
     * 修改数据
     * @param
     * @return
     */
    int update(UserPostsBO userPostsBO);


    /**
     * query
     * @param query
     * @return
     */
    List<UserPostsBO> findList(UserPostsQuery query);

    /**
     * query
     * @param query
     * @return
     */
    UserPostsBO findOne(UserPostsQuery query);

    PageListResult<UserPostsBO> findPage(UserPostsQuery query);
}