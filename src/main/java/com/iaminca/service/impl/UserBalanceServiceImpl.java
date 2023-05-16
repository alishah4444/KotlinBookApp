package com.iaminca.service.impl;

import com.iaminca.common.DelFlagEnum;
import com.iaminca.dal.dao.UserBalanceDAO;
import com.iaminca.dal.dataobject.UserBalanceDO;
import com.iaminca.query.UserBalanceQuery;
import com.iaminca.service.UserBalanceService;
import com.iaminca.service.bo.UserBalanceBO;
import com.iaminca.service.covert.UserBalanceConvert;
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
 * @date 2023-05-09 16:08:49
 */
@Service
public class UserBalanceServiceImpl implements UserBalanceService {

	@Resource
	private UserBalanceDAO userBalanceDAO;


    @Override
    public int add(UserBalanceBO userBalanceBO){
        userBalanceBO.setId(null);
        userBalanceBO.setDelFlag(DelFlagEnum.NOT_DEL.getCode());
        userBalanceBO.setCreateTime(new Date());
        userBalanceBO.setUpdateTime(userBalanceBO.getCreateTime());
		UserBalanceDO userBalanceDO = UserBalanceConvert.toDO(userBalanceBO);
		return userBalanceDAO.insert(userBalanceDO);
    }

    @Override
    public int update(UserBalanceBO userBalanceBO){
		UserBalanceDO userBalanceDO = UserBalanceConvert.toDO(userBalanceBO);
        return userBalanceDAO.updateByPrimaryKeySelective(userBalanceDO);
    }

    @Override
    public List<UserBalanceBO> findList(UserBalanceQuery query){
        List<UserBalanceDO> listByQuery = userBalanceDAO.selectByExample(this.convertExample(query));
        return UserBalanceConvert.toBOList(listByQuery);
    }
    /**
        *
        * @param userBalanceQuery
        * @return
        */
    private Example convertExample(UserBalanceQuery userBalanceQuery) {
        Example example = new Example(UserBalanceDO.class);
        Example.Criteria criteria = example.createCriteria();
        if (!ObjectUtils.isEmpty(userBalanceQuery.getId())) {
            criteria.andEqualTo("id", userBalanceQuery.getId());
        }
        if (!ObjectUtils.isEmpty(userBalanceQuery.getUserId())) {
            criteria.andEqualTo("userId", userBalanceQuery.getUserId());
        }
        return example;
    }

}
