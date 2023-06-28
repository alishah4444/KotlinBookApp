package com.iaminca.openai.service;

import com.iaminca.openai.query.ChatRequestMessageQuery;
import com.iaminca.openai.service.bo.ChatRequestMessageBO;

import java.util.List;

/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-04 09:54:43
 */
public interface ChatRequestMessageService {

    /**
	 * 添加数据
	 * @param
	 * @return
	 */
    int add(ChatRequestMessageBO chatRequestMessageBO);

    int add(List<ChatRequestMessageBO> chatRequestMessageBOList);

    /**
     * 修改数据
     * @param
     * @return
     */
    int update(ChatRequestMessageBO chatRequestMessageBO);


    /**
     * 分页查询
     * @param pagerCondition
     * @return
     */
    List<ChatRequestMessageBO> findList(ChatRequestMessageQuery pagerCondition);

}
