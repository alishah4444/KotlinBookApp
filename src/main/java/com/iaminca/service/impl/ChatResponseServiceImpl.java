package com.iaminca.service.impl;

import com.iaminca.dal.dao.ChatResponseDAO;
import com.iaminca.dal.dataobject.ChatResponseDO;
import com.iaminca.query.ChatResponseQuery;
import com.iaminca.service.ChatResponseService;
import com.iaminca.service.bo.ChatResponseBO;
import com.iaminca.service.covert.ChatResponseConvert;
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
public class ChatResponseServiceImpl implements ChatResponseService {

	@Resource
	private ChatResponseDAO chatResponseDAO;


    @Override
    public int add(ChatResponseBO chatResponseBO){
		return chatResponseDAO.insert(ChatResponseConvert.toDO(chatResponseBO));
    }

    @Override
    public int update(ChatResponseBO chatResponseBO){
        return chatResponseDAO.update(ChatResponseConvert.toDO(chatResponseBO));
    }

    @Override
    public List<ChatResponseBO> findList(ChatResponseQuery query) {
        List<ChatResponseDO> listByQuery = chatResponseDAO.findListByQuery(query);
        return ChatResponseConvert.toBOList(listByQuery);
    }


}
