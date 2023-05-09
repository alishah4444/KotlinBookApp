package com.iaminca.service;

import com.iaminca.query.UserQuery;
import com.iaminca.service.bo.UserBO;

import java.util.List;

/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:22
 */
public interface UserService {

    /**
	 * 添加数据
	 * @param
	 * @return
	 */
    UserBO add(UserBO userBO);

    /**
     * 修改数据
     * @param
     * @return
     */
    int update(UserBO userBO);


    /**
     * 分页查询
     * @param
     * @return
     */
    List<UserBO> findList(UserQuery query);

}
