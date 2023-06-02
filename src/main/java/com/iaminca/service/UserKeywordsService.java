package com.iaminca.service;

import com.iaminca.common.model.PageListResult;
import com.iaminca.query.UserKeywordsQuery;
import com.iaminca.service.bo.UserKeywordsBO;

import java.util.List;

/**
 * @author xw
 * @email xw
 * @date 2023-06-02 01:03:34
 */
public interface UserKeywordsService {

    /**
     * 添加数据
     *
     * @param
     * @return
     */
    int add(UserKeywordsBO userKeywordsBO);

    /**
     * 修改数据
     *
     * @param
     * @return
     */
    int update(UserKeywordsBO userKeywordsBO);


    /**
     * query
     *
     * @param query
     * @return
     */
    List<UserKeywordsBO> findList(UserKeywordsQuery query);
    UserKeywordsBO findOne(UserKeywordsQuery query);

    PageListResult<UserKeywordsBO> findPage(UserKeywordsQuery query);
}
