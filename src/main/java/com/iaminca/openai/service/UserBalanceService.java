package com.iaminca.openai.service;

import com.iaminca.openai.query.UserBalanceQuery;
import com.iaminca.openai.service.bo.UserBalanceBO;

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
