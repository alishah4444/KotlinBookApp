package com.iaminca.service.impl;

import com.iaminca.dal.dao.UserDAO;
import com.iaminca.dal.dataobject.UserDO;
import com.iaminca.query.UserQuery;
import com.iaminca.service.UserService;
import com.iaminca.service.bo.UserBO;
import com.iaminca.service.covert.UserConvert;
import org.springframework.stereotype.Service;

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
		return userDAO.insert(UserConvert.toDO(userBO));
    }

    @Override
    public int update(UserBO userBO){
        return userDAO.update(UserConvert.toDO(userBO));
    }

    @Override
    public List<UserBO> findList(UserQuery query) {
        List<UserDO> listByQuery = userDAO.findListByQuery(query);
        return UserConvert.toBOList(listByQuery);
    }


}
