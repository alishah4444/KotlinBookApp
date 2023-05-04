package com.iaminca.dal.dao;

import com.iaminca.dal.dataobject.ChatResponseChoicesDO;
import com.iaminca.query.ChatResponseChoicesQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:23
 */
public interface ChatResponseChoicesDAO {

    /**
     * 根据主键查询
     * @param id
	 * @return
	 */
	ChatResponseChoicesDO findByPrimaryKey(@Param("id") Long id);

    /**
     * 根据主键集查询
     * @param ids
     * @return
     */
	List<ChatResponseChoicesDO> findByIds(@Param("ids") Set<Long> ids);

    /**
     * 分页查询
     * @param condition
     * @return
     */
	List<ChatResponseChoicesDO> findList(ChatResponseChoicesDO condition);

    /**
     * 根据query查询结果集
     * @param chatResponseChoicesQuery
     * @return
     */
    List<ChatResponseChoicesDO> findListByQuery(ChatResponseChoicesQuery chatResponseChoicesQuery);

    /**
     * 新增
     * @param chatResponseChoicesDO
     * @return
     */
	int insert(ChatResponseChoicesDO chatResponseChoicesDO);

    /**
     * 更新
     * @param chatResponseChoicesDO
     * @return
     */
	int update(ChatResponseChoicesDO chatResponseChoicesDO);

}
