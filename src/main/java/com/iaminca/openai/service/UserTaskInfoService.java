package com.iaminca.openai.service;

import com.iaminca.openai.common.model.PageListResult;
import com.iaminca.openai.query.UserTaskInfoQuery;
import com.iaminca.openai.service.bo.UserTaskInfoBO;

import java.util.List;
/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-06-02 01:03:34
 */
public interface UserTaskInfoService {

    /**
	 * 添加数据
	 * @param
	 * @return
	 */
    int add(UserTaskInfoBO userTaskInfoBO);

    /**
     * 修改数据
     * @param
     * @return
     */
    int update(UserTaskInfoBO userTaskInfoBO);


    /**
     * query
     * @param query
     * @return
     */
    List<UserTaskInfoBO> findList(UserTaskInfoQuery query);

    UserTaskInfoBO findOne(UserTaskInfoQuery query);

    PageListResult<UserTaskInfoBO> findPage(UserTaskInfoQuery query);
}
