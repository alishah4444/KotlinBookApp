package com.iaminca.service.impl;

import javax.annotation.Resource;

import com.iaminca.dal.dao.ChatRequestDAO;
import com.iaminca.dal.dataobject.ChatRequestDO;
import com.iaminca.query.ChatRequestQuery;
import com.iaminca.service.ChatRequestService;
import com.iaminca.service.bo.ChatRequestBO;
import com.iaminca.service.covert.ChatRequestConvert;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 
 *
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:23
 */
@Service
public class ChatRequestServiceImpl implements ChatRequestService {

	@Resource
	private ChatRequestDAO chatRequestDAO;


    @Override
    public int add(ChatRequestBO chatRequestBO){
		return chatRequestDAO.insert(ChatRequestConvert.toDO(chatRequestBO));
    }

    @Override
    public int update(ChatRequestBO chatRequestBO){
        return chatRequestDAO.update(ChatRequestConvert.toDO(chatRequestBO));
    }

    @Override
    public List<ChatRequestBO> findList(ChatRequestQuery query) {
        List<ChatRequestDO> listByQuery = chatRequestDAO.findListByQuery(query);
        return ChatRequestConvert.toBOList(listByQuery);
    }


}
