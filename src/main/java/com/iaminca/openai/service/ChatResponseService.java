package com.iaminca.openai.service;

import com.iaminca.openai.service.bo.ChatResponseBO;
import com.iaminca.openai.query.ChatResponseQuery;

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
