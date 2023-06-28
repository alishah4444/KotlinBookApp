package com.iaminca.openai.service;

import com.iaminca.openai.common.model.PageListResult;
import com.iaminca.openai.service.bo.UserKeyBO;
import com.iaminca.openai.query.UserKeyQuery;

import java.util.List;
/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-04 17:34:01
 */
public interface UserKeyService {

    /**
	 * 添加数据
	 * @param
	 * @return
	 */
    int add(UserKeyBO userKeyBO);

    /**
     * 修改数据
     * @param
     * @return
     */
    int update(UserKeyBO userKeyBO);


    /**
     * query
     * @param query
     * @return
     */
    List<UserKeyBO> findList(UserKeyQuery query);

    public PageListResult<UserKeyBO> findPage(UserKeyQuery pagerCondition);

}
