package com.iaminca.service;

import com.iaminca.common.model.PageListResult;
import com.iaminca.query.UserRechargeQuery;
import com.iaminca.service.bo.UserRechargeBO;

import java.util.List;
/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-16 11:06:12
 */
public interface UserRechargeService {

    /**
	 * 添加数据
	 * @param
	 * @return
	 */
    int add(UserRechargeBO userRechargeBO);

    /**
     * 修改数据
     * @param
     * @return
     */
    int update(UserRechargeBO userRechargeBO);


    /**
     * query
     * @param query
     * @return
     */
    List<UserRechargeBO> findList(UserRechargeQuery query);

    public PageListResult<UserRechargeBO> findPage(UserRechargeQuery query);

}
