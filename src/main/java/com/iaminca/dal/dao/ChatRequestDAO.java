package com.iaminca.dal.dao;

import com.iaminca.dal.dataobject.ChatRequestDO;
import com.iaminca.query.ChatRequestQuery;
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
public interface ChatRequestDAO {

    /**
     * 根据主键查询
     * @param id
	 * @return
	 */
	ChatRequestDO findByPrimaryKey(@Param("id") Long id);

    /**
     * 根据主键集查询
     * @param ids
     * @return
     */
	List<ChatRequestDO> findByIds(@Param("ids") Set<Long> ids);

    /**
     *
     * @param condition
     * @return
     */
	List<ChatRequestDO> findList(ChatRequestDO chatRequestDO);

    /**
     * 根据query查询结果集
     * @param chatRequestQuery
     * @return
     */
    List<ChatRequestDO> findListByQuery(ChatRequestQuery chatRequestQuery);

    /**
     * 新增
     * @param chatRequestDO
     * @return
     */
	int insert(ChatRequestDO chatRequestDO);

    /**
     * 更新
     * @param chatRequestDO
     * @return
     */
	int update(ChatRequestDO chatRequestDO);

}
