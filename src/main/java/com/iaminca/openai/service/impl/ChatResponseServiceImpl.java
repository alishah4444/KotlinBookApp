package com.iaminca.openai.service.impl;

import com.iaminca.openai.common.DelFlagEnum;
import com.iaminca.openai.dal.dao.ChatResponseDAO;
import com.iaminca.openai.dal.dataobject.ChatResponseDO;
import com.iaminca.openai.service.covert.ChatResponseConvert;
import com.iaminca.openai.query.ChatResponseQuery;
import com.iaminca.openai.service.ChatResponseService;
import com.iaminca.openai.service.bo.ChatResponseBO;
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
public class ChatResponseServiceImpl implements ChatResponseService {

	@Resource
	private ChatResponseDAO chatResponseDAO;


    @Override
    public ChatResponseBO add(ChatResponseBO chatResponseBO){
        chatResponseBO.setId(null);
        chatResponseBO.setDelFlag(DelFlagEnum.NOT_DEL.getCode());
        chatResponseBO.setCreateTime(new Date());
        chatResponseBO.setUpdateTime(chatResponseBO.getCreateTime());
        ChatResponseDO chatResponseDO = ChatResponseConvert.toDO(chatResponseBO);
        chatResponseDAO.insert(chatResponseDO);
		return ChatResponseConvert.toBO(chatResponseDO);
    }

    @Override
    public int update(ChatResponseBO chatResponseBO){
        return chatResponseDAO.updateByPrimaryKeySelective(ChatResponseConvert.toDO(chatResponseBO));
    }

    @Override
    public List<ChatResponseBO> findList(ChatResponseQuery query) {
        List<ChatResponseDO> listByQuery = chatResponseDAO.selectByExample(this.convertExample(query));
        return ChatResponseConvert.toBOList(listByQuery);
    }

    /**
     *
     * @param chatResponseQuery
     * @return
     */
    private Example convertExample(ChatResponseQuery chatResponseQuery) {
        Example example = new Example(ChatResponseDO.class);
        Example.Criteria criteria = example.createCriteria();
        if (!ObjectUtils.isEmpty(chatResponseQuery.getId())) {
            criteria.andEqualTo("id", chatResponseQuery.getId());
        }
        if (!ObjectUtils.isEmpty(chatResponseQuery.getRecordId())) {
            criteria.andEqualTo("recordId", chatResponseQuery.getRecordId());
        }
        return example;
    }


}
