package com.iaminca.service.impl;

import com.iaminca.common.DelFlagEnum;
import com.iaminca.dal.dao.UserKeyDAO;
import com.iaminca.dal.dataobject.UserKeyDO;
import com.iaminca.query.UserKeyQuery;
import com.iaminca.service.UserKeyService;
import com.iaminca.service.bo.UserKeyBO;
import com.iaminca.service.covert.UserKeyConvert;
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
 * @date 2023-05-04 17:34:01
 */
@Service
public class UserKeyServiceImpl implements UserKeyService {

	@Resource
	private UserKeyDAO userKeyDAO;


    @Override
    public int add(UserKeyBO userKeyBO){
        userKeyBO.setId(null);
        userKeyBO.setDelFlag(DelFlagEnum.NOT_DEL.getCode());
        userKeyBO.setCreateTime(new Date());
        userKeyBO.setUpdateTime(userKeyBO.getCreateTime());
		UserKeyDO userKeyDO = UserKeyConvert.toDO(userKeyBO);
		return userKeyDAO.insert(userKeyDO);
    }

    @Override
    public int update(UserKeyBO userKeyBO){
		UserKeyDO userKeyDO = UserKeyConvert.toDO(userKeyBO);
        return userKeyDAO.updateByPrimaryKey(userKeyDO);
    }

    @Override
    public List<UserKeyBO> findList(UserKeyQuery query){
        List<UserKeyDO> listByQuery = userKeyDAO.selectByExample(this.convertExample(query));
        return UserKeyConvert.toBOList(listByQuery);
    }
    /**
        *
        * @param userKeyQuery
        * @return
        */
    private Example convertExample(UserKeyQuery userKeyQuery) {
        Example example = new Example(UserKeyDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag", DelFlagEnum.NOT_DEL);
        if (!ObjectUtils.isEmpty(userKeyQuery.getId())) {
            criteria.andEqualTo("id", userKeyQuery.getId());
        }
        if (!ObjectUtils.isEmpty(userKeyQuery.getUserKey())) {
            criteria.andEqualTo("userKey", userKeyQuery.getUserKey());
        }
        return example;
    }

}
