package com.iaminca.service.impl;

import com.iaminca.dal.dao.ChatRequestDAO;
import com.iaminca.dal.dataobject.ChatRequestDO;
import com.iaminca.query.ChatRequestQuery;
import com.iaminca.service.ChatRequestService;
import com.iaminca.service.bo.ChatRequestBO;
import com.iaminca.service.covert.ChatRequestConvert;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

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
public class ChatRequestServiceImpl implements ChatRequestService {

	@Resource
	private ChatRequestDAO chatRequestDAO;


    @Override
    public ChatRequestBO add(ChatRequestBO chatRequestBO){
        chatRequestBO.setId(null);
        ChatRequestDO chatRequestDO = ChatRequestConvert.toDO(chatRequestBO);
        chatRequestDAO.insert(chatRequestDO);
		return ChatRequestConvert.toBO(chatRequestDO);
    }

    @Override
    public int update(ChatRequestBO chatRequestBO){
        return chatRequestDAO.updateByPrimaryKey(ChatRequestConvert.toDO(chatRequestBO));
    }

    @Override
    public List<ChatRequestBO> findList(ChatRequestQuery query) {
        List<ChatRequestDO> listByQuery = chatRequestDAO.selectByExample(this.convertExample(query));
        return ChatRequestConvert.toBOList(listByQuery);
    }

    /**
     *
     * @param chatRequestQuery
     * @return
     */
    private Example convertExample(ChatRequestQuery chatRequestQuery) {
        Example example = new Example(ChatRequestDO.class);
        Example.Criteria criteria = example.createCriteria();
        if (!ObjectUtils.isEmpty(chatRequestQuery.getId())) {
            criteria.andEqualTo("id", chatRequestQuery.getId());
        }
        return example;
    }
}
