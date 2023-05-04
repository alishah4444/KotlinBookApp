package com.iaminca.service;

import com.iaminca.query.ChatResponseQuery;
import com.iaminca.service.bo.ChatResponseBO;

import java.util.List;

/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:23
 */
public interface ChatResponseService {

    /**
	 * 添加数据
	 * @param
	 * @return
	 */
    ChatResponseBO add(ChatResponseBO chatResponseBO);

    /**
     * 修改数据
     * @param
     * @return
     */
    int update(ChatResponseBO chatResponseBO);


    /**
     *
     * @param pagerCondition
     * @return
     */
    List<ChatResponseBO> findList(ChatResponseQuery pagerCondition);

}
