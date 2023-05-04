package com.iaminca.dal.dao;

import com.iaminca.dal.dataobject.UserDO;
import com.iaminca.query.UserQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:22
 */
public interface UserDAO {

    /**
     * 根据主键查询
     * @param id
	 * @return
	 */
	UserDO findByPrimaryKey(@Param("id") Long id);

    /**
     * 根据主键集查询
     * @param ids
     * @return
     */
	List<UserDO> findByIds(@Param("ids") Set<Long> ids);

    /**
     * 分页查询
     * @param condition
     * @return
     */
	List<UserDO> findList(UserDO condition);

    /**
     * 根据query查询结果集
     * @param userQuery
     * @return
     */
    List<UserDO> findListByQuery(UserQuery userQuery);

    /**
     * 新增
     * @param userDO
     * @return
     */
	int insert(UserDO userDO);

    /**
     * 更新
     * @param userDO
     * @return
     */
	int update(UserDO userDO);

}
