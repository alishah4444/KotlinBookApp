package com.iaminca.openai.service.impl;

import com.github.pagehelper.Page;
import com.iaminca.openai.common.Constants;
import com.iaminca.openai.common.model.PageHelperAdaptor;
import com.iaminca.openai.common.model.PageListResult;
import com.iaminca.openai.dal.dao.UserApplyGpt4DAO;
import com.iaminca.openai.dal.dataobject.UserApplyGpt4DO;
import com.iaminca.openai.query.UserApplyGpt4Query;
import com.iaminca.openai.service.UserApplyGpt4Service;
import com.iaminca.openai.service.bo.UserApplyGpt4BO;
import com.iaminca.openai.service.covert.UserApplyGpt4Convert;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * 
 *
 * @author xw
 * @email xw
 * @date 2023-05-16 18:59:03
 */
@Service
public class UserApplyGpt4ServiceImpl implements UserApplyGpt4Service {

	@Resource
	private UserApplyGpt4DAO userApplyGpt4DAO;


    @Override
    public int add(UserApplyGpt4BO userApplyGpt4BO){
        userApplyGpt4BO.setId(null);
		UserApplyGpt4DO userApplyGpt4DO = UserApplyGpt4Convert.toDO(userApplyGpt4BO);
		return userApplyGpt4DAO.insert(userApplyGpt4DO);
    }

    @Override
    public int update(UserApplyGpt4BO userApplyGpt4BO){
		UserApplyGpt4DO userApplyGpt4DO = UserApplyGpt4Convert.toDO(userApplyGpt4BO);
        return userApplyGpt4DAO.updateByPrimaryKeySelective(userApplyGpt4DO);
    }

    @Override
    public List<UserApplyGpt4BO> findList(UserApplyGpt4Query query){
        List<UserApplyGpt4DO> listByQuery = userApplyGpt4DAO.selectByExample(this.convertExample(query));
        return UserApplyGpt4Convert.toBOList(listByQuery);
    }

    @Override
    public PageListResult<UserApplyGpt4BO> findPage(UserApplyGpt4Query pagerCondition){
        //分页页码设置
        pagerCondition.setOrderBy(Constants.ORDER_BY);
        Page page = PageHelperAdaptor.preparePage(pagerCondition, Boolean.TRUE);
        page.setReasonable(Boolean.FALSE);
        List<UserApplyGpt4DO> list = userApplyGpt4DAO.selectByExample(this.convertExample(pagerCondition));
        //结果集设置
        PageListResult<UserApplyGpt4BO> pageListResult = new PageListResult(UserApplyGpt4Convert.toBOList(list));
        PageHelperAdaptor.setPageResult(page, pageListResult);
        pageListResult.setPageNum(pagerCondition.getPageNum());
        pageListResult.setPageSize(pagerCondition.getPageSize());
        return pageListResult;
    }
    /**
        *
        * @param userApplyGpt4Query
        * @return
        */
    private Example convertExample(UserApplyGpt4Query userApplyGpt4Query) {
        Example example = new Example(UserApplyGpt4DO.class);
        Example.Criteria criteria = example.createCriteria();
        if (!ObjectUtils.isEmpty(userApplyGpt4Query.getId())) {
            criteria.andEqualTo("id", userApplyGpt4Query.getId());
        }
        return example;
    }

}
