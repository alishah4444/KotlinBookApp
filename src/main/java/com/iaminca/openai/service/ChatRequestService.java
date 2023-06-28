package com.iaminca.openai.service;

import com.iaminca.openai.query.ChatRequestQuery;
import com.iaminca.openai.service.bo.ChatRequestBO;

import java.util.List;

/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:23
 */
public interface ChatRequestService {

    /**
	 * 添加数据
	 * @param
	 * @return
	 */
    ChatRequestBO add(ChatRequestBO chatRequestBO);

    /**
     * 修改数据
     * @param
     * @return
     */
    int update(ChatRequestBO chatRequestBO);


    /**
     * 分页查询
     * @param
     * @return
     */
    List<ChatRequestBO> findList(ChatRequestQuery query);

}
