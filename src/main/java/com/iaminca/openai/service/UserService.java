package com.iaminca.openai.service;

import com.iaminca.openai.service.bo.UserBO;
import com.iaminca.openai.query.UserQuery;

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
