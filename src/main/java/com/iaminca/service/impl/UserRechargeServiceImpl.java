package com.iaminca.service.impl;

import javax.annotation.Resource;

import com.github.pagehelper.Page;
import com.iaminca.common.Constants;
import com.iaminca.common.DelFlagEnum;
import com.iaminca.common.model.PageHelperAdaptor;
import com.iaminca.common.model.PageListResult;
import com.iaminca.dal.dao.UserRechargeDAO;
import com.iaminca.dal.dataobject.UserKeyDO;
import com.iaminca.dal.dataobject.UserRechargeDO;
import com.iaminca.query.UserRechargeQuery;
import com.iaminca.service.UserRechargeService;
import com.iaminca.service.bo.UserKeyBO;
import com.iaminca.service.bo.UserRechargeBO;
import com.iaminca.service.covert.UserKeyConvert;
import com.iaminca.service.covert.UserRechargeConvert;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;


/**
 * 
 *
 * @author xw
 * @email xw
 * @date 2023-05-16 11:06:12
 */
@Service
public class UserRechargeServiceImpl implements UserRechargeService {

	@Resource
	private UserRechargeDAO userRechargeDAO;


    @Override
    public int add(UserRechargeBO userRechargeBO){
        userRechargeBO.setId(null);
        userRechargeBO.setDelFlag(DelFlagEnum.NOT_DEL.getCode());
        userRechargeBO.setCreateTime(new Date());
        userRechargeBO.setUpdateTime(userRechargeBO.getCreateTime());
		UserRechargeDO userRechargeDO = UserRechargeConvert.toDO(userRechargeBO);
		return userRechargeDAO.insert(userRechargeDO);
    }

    @Override
    public int update(UserRechargeBO userRechargeBO){
		UserRechargeDO userRechargeDO = UserRechargeConvert.toDO(userRechargeBO);
        return userRechargeDAO.updateByPrimaryKeySelective(userRechargeDO);
    }

    @Override
    public List<UserRechargeBO> findList(UserRechargeQuery query){
        List<UserRechargeDO> listByQuery = userRechargeDAO.selectByExample(this.convertExample(query));
        return UserRechargeConvert.toBOList(listByQuery);
    }

    @Override
    public PageListResult<UserRechargeBO> findPage(UserRechargeQuery pagerCondition) {
        //分页页码设置
        pagerCondition.setOrderBy(Constants.ORDER_BY);
        Page page = PageHelperAdaptor.preparePage(pagerCondition, Boolean.TRUE);
        page.setReasonable(Boolean.FALSE);
        List<UserRechargeDO> list = userRechargeDAO.selectByExample(this.convertExample(pagerCondition));
        //结果集设置
        PageListResult<UserRechargeBO> pageListResult = new PageListResult(UserRechargeConvert.toBOList(list));
        PageHelperAdaptor.setPageResult(page, pageListResult);
        pageListResult.setPageNum(pagerCondition.getPageNum());
        pageListResult.setPageSize(pagerCondition.getPageSize());
        return pageListResult;
    }

    /**
        *
        * @param userRechargeQuery
        * @return
        */
    private Example convertExample(UserRechargeQuery userRechargeQuery) {
        Example example = new Example(UserRechargeDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag", DelFlagEnum.NOT_DEL.getCode());
        if (!ObjectUtils.isEmpty(userRechargeQuery.getId())) {
            criteria.andEqualTo("id", userRechargeQuery.getId());
        }
        return example;
    }

}
