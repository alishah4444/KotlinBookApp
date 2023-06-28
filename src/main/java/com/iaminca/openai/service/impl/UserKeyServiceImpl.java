package com.iaminca.openai.service.impl;

import com.github.pagehelper.Page;
import com.iaminca.openai.common.Constants;
import com.iaminca.openai.common.DelFlagEnum;
import com.iaminca.openai.common.model.PageHelperAdaptor;
import com.iaminca.openai.common.model.PageListResult;
import com.iaminca.openai.dal.dao.UserKeyDAO;
import com.iaminca.openai.dal.dataobject.UserKeyDO;
import com.iaminca.openai.service.UserKeyService;
import com.iaminca.openai.service.bo.UserKeyBO;
import com.iaminca.openai.service.covert.UserKeyConvert;
import com.iaminca.openai.query.UserKeyQuery;
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
        return userKeyDAO.updateByPrimaryKeySelective(userKeyDO);
    }

    @Override
    public List<UserKeyBO> findList(UserKeyQuery query){
        List<UserKeyDO> listByQuery = userKeyDAO.selectByExample(this.convertExample(query));
        return UserKeyConvert.toBOList(listByQuery);
    }

    @Override
    public PageListResult<UserKeyBO> findPage(UserKeyQuery pagerCondition){
        //分页页码设置
        pagerCondition.setOrderBy(Constants.ORDER_BY);
        Page page = PageHelperAdaptor.preparePage(pagerCondition, Boolean.TRUE);
        page.setReasonable(Boolean.FALSE);
        List<UserKeyDO> list = userKeyDAO.selectByExample(this.convertExample(pagerCondition));
        //结果集设置
        PageListResult<UserKeyBO> pageListResult = new PageListResult(UserKeyConvert.toBOList(list));
        PageHelperAdaptor.setPageResult(page, pageListResult);
        pageListResult.setPageNum(pagerCondition.getPageNum());
        pageListResult.setPageSize(pagerCondition.getPageSize());
        return pageListResult;
    }
    /**
        *
        * @param userKeyQuery
        * @return
        */
    private Example convertExample(UserKeyQuery userKeyQuery) {
        Example example = new Example(UserKeyDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag", DelFlagEnum.NOT_DEL.getCode());
        if (!ObjectUtils.isEmpty(userKeyQuery.getId())) {
            criteria.andEqualTo("id", userKeyQuery.getId());
        }
        if (!ObjectUtils.isEmpty(userKeyQuery.getUserKey())) {
            criteria.andEqualTo("userKey", userKeyQuery.getUserKey());
        }
        if (!ObjectUtils.isEmpty(userKeyQuery.getUserId())) {
            criteria.andEqualTo("userId", userKeyQuery.getUserId());
        }
        return example;
    }

}
