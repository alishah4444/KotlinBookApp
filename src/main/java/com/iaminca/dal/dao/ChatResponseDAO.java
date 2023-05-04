package com.iaminca.dal.dao;

import com.iaminca.dal.dataobject.ChatResponseDO;
import com.iaminca.query.ChatResponseQuery;
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
public interface ChatResponseDAO {

    /**
     * 根据主键查询
     * @param id
	 * @return
	 */
	ChatResponseDO findByPrimaryKey(@Param("id") Long id);

    /**
     * 根据主键集查询
     * @param ids
     * @return
     */
	List<ChatResponseDO> findByIds(@Param("ids") Set<Long> ids);

    /**
     * 分页查询
     * @param condition
     * @return
     */
	List<ChatResponseDO> findList(ChatResponseDO chatResponseDO);

    /**
     * 根据query查询结果集
     * @param chatResponseQuery
     * @return
     */
    List<ChatResponseDO> findListByQuery(ChatResponseQuery chatResponseQuery);

    /**
     * 新增
     * @param chatResponseDO
     * @return
     */
	int insert(ChatResponseDO chatResponseDO);

    /**
     * 更新
     * @param chatResponseDO
     * @return
     */
	int update(ChatResponseDO chatResponseDO);

}
