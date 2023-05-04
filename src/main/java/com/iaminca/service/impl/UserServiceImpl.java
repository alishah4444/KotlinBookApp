package com.iaminca.service.impl;

import com.iaminca.dal.dao.UserDAO;
import com.iaminca.dal.dataobject.UserDO;
import com.iaminca.query.UserQuery;
import com.iaminca.service.UserService;
import com.iaminca.service.bo.UserBO;
import com.iaminca.service.covert.UserConvert;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;


/**
 * 
 *
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:22
 */
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDAO userDAO;


    @Override
    public int add(UserBO userBO){
        userBO.setId(null);
		return userDAO.insert(UserConvert.toDO(userBO));
    }

    @Override
    public int update(UserBO userBO){
        return userDAO.updateByPrimaryKey(UserConvert.toDO(userBO));
    }

    @Override
    public List<UserBO> findList(UserQuery query) {
        List<UserDO> listByQuery = userDAO.selectByExample(convertExample(query));
        return UserConvert.toBOList(listByQuery);
    }

    /**
     * 查询条件
     * @param userQuery
     * @return
     */
    private Example convertExample(UserQuery userQuery) {
        Example example = new Example(UserDO.class);
        Example.Criteria criteria = example.createCriteria();
        if (!ObjectUtils.isEmpty(userQuery.getId())) {
            criteria.andEqualTo("id", userQuery.getId());
        }
        return example;
    }

}
