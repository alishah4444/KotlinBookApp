package com.iaminca.openai.service.impl;

import com.iaminca.openai.common.DelFlagEnum;
import com.iaminca.openai.dal.dao.ChatRequestDAO;
import com.iaminca.openai.dal.dataobject.ChatRequestDO;
import com.iaminca.openai.query.ChatRequestQuery;
import com.iaminca.openai.service.bo.ChatRequestBO;
import com.iaminca.openai.service.covert.ChatRequestConvert;
import com.iaminca.openai.service.ChatRequestService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
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
        chatRequestBO.setDelFlag(DelFlagEnum.NOT_DEL.getCode());
        chatRequestBO.setCreateTime(new Date());
        chatRequestBO.setUpdateTime(chatRequestBO.getCreateTime());
        ChatRequestDO chatRequestDO = ChatRequestConvert.toDO(chatRequestBO);
        chatRequestDAO.insert(chatRequestDO);
		return ChatRequestConvert.toBO(chatRequestDO);
    }

    @Override
    public int update(ChatRequestBO chatRequestBO){
        return chatRequestDAO.updateByPrimaryKeySelective(ChatRequestConvert.toDO(chatRequestBO));
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
        if (!ObjectUtils.isEmpty(chatRequestQuery.getRecordId())) {
            criteria.andEqualTo("recordId", chatRequestQuery.getRecordId());
        }
        return example;
    }
}
