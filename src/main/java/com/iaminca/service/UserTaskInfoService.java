package com.iaminca.service;

import com.iaminca.common.model.PageListResult;
import com.iaminca.query.UserTaskInfoQuery;
import com.iaminca.service.bo.UserTaskInfoBO;

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

    PageListResult<UserTaskInfoBO> findPage(UserTaskInfoQuery query);
}
