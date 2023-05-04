package com.iaminca.service.impl;

import com.iaminca.dal.dao.ChatResponseDAO;
import com.iaminca.dal.dataobject.ChatResponseDO;
import com.iaminca.query.ChatResponseQuery;
import com.iaminca.service.ChatResponseService;
import com.iaminca.service.bo.ChatResponseBO;
import com.iaminca.service.covert.ChatResponseConvert;
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
public class ChatResponseServiceImpl implements ChatResponseService {

	@Resource
	private ChatResponseDAO chatResponseDAO;


    @Override
    public int add(ChatResponseBO chatResponseBO){
        chatResponseBO.setId(null);
		return chatResponseDAO.insert(ChatResponseConvert.toDO(chatResponseBO));
    }

    @Override
    public int update(ChatResponseBO chatResponseBO){
        return chatResponseDAO.updateByPrimaryKey(ChatResponseConvert.toDO(chatResponseBO));
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
        return example;
    }


}
