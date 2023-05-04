package com.iaminca.service.impl;

import com.iaminca.dal.dao.ChatResponseChoicesDAO;
import com.iaminca.dal.dao.ChatResponseChoicesListDAO;
import com.iaminca.dal.dataobject.ChatResponseChoicesDO;
import com.iaminca.query.ChatResponseChoicesQuery;
import com.iaminca.service.ChatResponseChoicesService;
import com.iaminca.service.bo.ChatResponseChoicesBO;
import com.iaminca.service.covert.ChatResponseChoicesConvert;
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
public class ChatResponseChoicesServiceImpl implements ChatResponseChoicesService {

	@Resource
	private ChatResponseChoicesDAO chatResponseChoicesDAO;
    @Resource
    private ChatResponseChoicesListDAO chatResponseChoicesListDAO;


    @Override
    public int add(ChatResponseChoicesBO chatResponseChoicesBO){
        chatResponseChoicesBO.setId(null);
		return chatResponseChoicesDAO.insert(ChatResponseChoicesConvert.toDO(chatResponseChoicesBO));
    }

    @Override
    public int addList(List<ChatResponseChoicesBO> chatResponseChoicesBOList) {
        return chatResponseChoicesListDAO.insertList(ChatResponseChoicesConvert.toDOList(chatResponseChoicesBOList));
    }

    @Override
    public int update(ChatResponseChoicesBO chatResponseChoicesBO){
        return chatResponseChoicesDAO.updateByPrimaryKey(ChatResponseChoicesConvert.toDO(chatResponseChoicesBO));
    }

    @Override
    public List<ChatResponseChoicesBO> findList(ChatResponseChoicesQuery query) {
        List<ChatResponseChoicesDO> listByQuery = chatResponseChoicesDAO.selectByExample(this.convertExample(query));
        return ChatResponseChoicesConvert.toBOList(listByQuery);
    }


    /**
     *
     * @param chatResponseChoicesQuery
     * @return
     */
    private Example convertExample(ChatResponseChoicesQuery chatResponseChoicesQuery) {
        Example example = new Example(ChatResponseChoicesDO.class);
        Example.Criteria criteria = example.createCriteria();
        if (!ObjectUtils.isEmpty(chatResponseChoicesQuery.getId())) {
            criteria.andEqualTo("id", chatResponseChoicesQuery.getId());
        }
        return example;
    }

}
