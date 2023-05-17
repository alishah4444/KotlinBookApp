package com.iaminca.service;

import com.iaminca.common.model.PageListResult;
import com.iaminca.query.UserApplyGpt4Query;
import com.iaminca.service.bo.UserApplyGpt4BO;

import java.util.List;
/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-16 18:59:03
 */
public interface UserApplyGpt4Service {

    /**
	 * 添加数据
	 * @param
	 * @return
	 */
    int add(UserApplyGpt4BO userApplyGpt4BO);

    /**
     * 修改数据
     * @param
     * @return
     */
    int update(UserApplyGpt4BO userApplyGpt4BO);


    /**
     * query
     * @param query
     * @return
     */
    List<UserApplyGpt4BO> findList(UserApplyGpt4Query query);

    PageListResult<UserApplyGpt4BO> findPage(UserApplyGpt4Query pagerCondition);

}
