package com.iaminca.service.impl;

import com.iaminca.dal.dao.ChatResponseChoicesDAO;
import com.iaminca.dal.dataobject.ChatResponseChoicesDO;
import com.iaminca.query.ChatResponseChoicesQuery;
import com.iaminca.service.ChatResponseChoicesService;
import com.iaminca.service.bo.ChatResponseChoicesBO;
import com.iaminca.service.covert.ChatResponseChoicesConvert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 
 *
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:23
 */
@Service
public class ChatResponseChoicesServiceImpl implements ChatResponseChoicesService {

	@Resource
	private ChatResponseChoicesDAO chatResponseChoicesDAO;


    @Override
    public int add(ChatResponseChoicesBO chatResponseChoicesBO){
		return chatResponseChoicesDAO.insert(ChatResponseChoicesConvert.toDO(chatResponseChoicesBO));
    }

    @Override
    public int update(ChatResponseChoicesBO chatResponseChoicesBO){
        return chatResponseChoicesDAO.update(ChatResponseChoicesConvert.toDO(chatResponseChoicesBO));
    }

    @Override
    public List<ChatResponseChoicesBO> findList(ChatResponseChoicesQuery query) {
        List<ChatResponseChoicesDO> listByQuery = chatResponseChoicesDAO.findListByQuery(query);
        return ChatResponseChoicesConvert.toBOList(listByQuery);
    }

}
