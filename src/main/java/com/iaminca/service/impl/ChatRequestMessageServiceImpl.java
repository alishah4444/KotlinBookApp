package com.iaminca.service.impl;

import com.iaminca.common.DelFlagEnum;
import com.iaminca.dal.dao.ChatRequestMessageDAO;
import com.iaminca.dal.dao.ChatRequestMessageListDAO;
import com.iaminca.dal.dataobject.ChatRequestMessageDO;
import com.iaminca.query.ChatRequestMessageQuery;
import com.iaminca.service.ChatRequestMessageService;
import com.iaminca.service.bo.ChatRequestMessageBO;
import com.iaminca.service.covert.ChatRequestMessageConvert;
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
 * @date 2023-05-04 09:54:43
 */
@Service
public class ChatRequestMessageServiceImpl implements ChatRequestMessageService {

	@Resource
	private ChatRequestMessageDAO chatRequestMessageDAO;
    @Resource
    private ChatRequestMessageListDAO chatRequestMessageListDAO;


    @Override
    public int add(ChatRequestMessageBO chatRequestMessageBO){
        chatRequestMessageBO.setId(null);
        chatRequestMessageBO.setDelFlag(DelFlagEnum.NOT_DEL.getCode());
        chatRequestMessageBO.setCreateTime(new Date());
        chatRequestMessageBO.setUpdateTime(chatRequestMessageBO.getCreateTime());
		return chatRequestMessageDAO.insert(ChatRequestMessageConvert.toDO(chatRequestMessageBO));
    }

    @Override
    public int add(List<ChatRequestMessageBO> chatRequestMessageBOList) {
        return chatRequestMessageListDAO.insertList(ChatRequestMessageConvert.toDOList(chatRequestMessageBOList));
    }

    @Override
    public int update(ChatRequestMessageBO chatRequestMessageBO){
        return chatRequestMessageDAO.updateByPrimaryKeySelective(ChatRequestMessageConvert.toDO(chatRequestMessageBO));
    }

    @Override
    public List<ChatRequestMessageBO> findList(ChatRequestMessageQuery query) {
        List<ChatRequestMessageDO> chatRequestMessageDOS = chatRequestMessageDAO.selectByExample(this.convertExample(query));
        return ChatRequestMessageConvert.toBOList(chatRequestMessageDOS);
    }

    /**
     *
     * @param chatRequestQuery
     * @return
     */
    private Example convertExample(ChatRequestMessageQuery chatRequestQuery) {
        Example example = new Example(ChatRequestMessageDO.class);
        Example.Criteria criteria = example.createCriteria();
        if (!ObjectUtils.isEmpty(chatRequestQuery.getId())) {
            criteria.andEqualTo("id", chatRequestQuery.getId());
        }
        return example;
    }

}
