package com.iaminca.service;

import com.iaminca.query.UserBalanceQuery;
import com.iaminca.service.bo.UserBalanceBO;

import java.util.List;
/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-09 16:08:49
 */
public interface UserBalanceService {

    /**
	 * 添加数据
	 * @param
	 * @return
	 */
    int add(UserBalanceBO userBalanceBO);

    /**
     * 修改数据
     * @param
     * @return
     */
    int update(UserBalanceBO userBalanceBO);


    /**
     * query
     * @param query
     * @return
     */
    List<UserBalanceBO> findList(UserBalanceQuery query);

}
