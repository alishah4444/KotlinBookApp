package com.iaminca.service;

import com.iaminca.query.ChatResponseChoicesQuery;
import com.iaminca.service.bo.ChatResponseChoicesBO;

import java.util.List;

/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:23
 */
public interface ChatResponseChoicesService {

    /**
	 * 添加数据
	 * @param
	 * @return
	 */
    int add(ChatResponseChoicesBO chatResponseChoicesBO);
    int addList(List<ChatResponseChoicesBO> chatResponseChoicesBOList);

    /**
     * 修改数据
     * @param
     * @return
     */
    int update(ChatResponseChoicesBO chatResponseChoicesBO);


    /**
     * 分页查询
     * @param
     * @return
     */
    List<ChatResponseChoicesBO> findList(ChatResponseChoicesQuery query);

}
