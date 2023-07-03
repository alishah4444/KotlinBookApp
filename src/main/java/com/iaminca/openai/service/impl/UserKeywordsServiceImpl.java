package com.iaminca.openai.service.impl;

import com.github.pagehelper.Page;
import com.iaminca.openai.common.Constants;
import com.iaminca.openai.common.DelFlagEnum;
import com.iaminca.openai.common.model.PageHelperAdaptor;
import com.iaminca.openai.common.model.PageListResult;
import com.iaminca.openai.dal.dao.UserKeywordsDAO;
import com.iaminca.openai.dal.dao.UserKeywordsListDAO;
import com.iaminca.openai.dal.dataobject.UserKeywordsDO;
import com.iaminca.openai.dal.dataobject.UserKeywordsListDO;
import com.iaminca.openai.service.covert.UserKeywordsListConvert;
import com.iaminca.openai.query.UserKeywordsQuery;
import com.iaminca.openai.service.UserKeywordsService;
import com.iaminca.openai.service.bo.UserKeywordsBO;
import com.iaminca.openai.service.covert.UserKeywordsConvert;
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
 * @date 2023-06-02 01:03:34
 */
@Service
public class UserKeywordsServiceImpl implements UserKeywordsService {

	@Resource
	private UserKeywordsDAO userKeywordsDAO;
    @Resource
    private UserKeywordsListDAO userKeywordsListDAO;


    @Override
    public int add(UserKeywordsBO userKeywordsBO){
        userKeywordsBO.setId(null);
        userKeywordsBO.setDelFlag(DelFlagEnum.NOT_DEL.getCode());
        userKeywordsBO.setCreateTime(new Date());
        userKeywordsBO.setUpdateTime( userKeywordsBO.getCreateTime());
        UserKeywordsDO userKeywordsDO = UserKeywordsConvert.toDO(userKeywordsBO);
		return userKeywordsDAO.insert(userKeywordsDO);
    }

    @Override
    public int update(UserKeywordsBO userKeywordsBO){
		UserKeywordsDO userKeywordsDO = UserKeywordsConvert.toDO(userKeywordsBO);
        return userKeywordsDAO.updateByPrimaryKeySelective(userKeywordsDO);
    }

    @Override
    public List<UserKeywordsBO> findList(UserKeywordsQuery query){
        List<UserKeywordsListDO> listByQuery = userKeywordsListDAO.selectByExample(this.convertListExample(query));
        return UserKeywordsListConvert.toBOList(listByQuery);
    }

    @Override
    public UserKeywordsBO findOne(UserKeywordsQuery query) {
        UserKeywordsDO userKeywordsDO = userKeywordsDAO.selectOneByExample(this.convertExample(query));
        return UserKeywordsConvert.toBO(userKeywordsDO);
    }

    @Override
    public PageListResult<UserKeywordsBO> findPage(UserKeywordsQuery pagerCondition){
        //分页页码设置
        pagerCondition.setOrderBy(Constants.ORDER_BY);
        Page page = PageHelperAdaptor.preparePage(pagerCondition, Boolean.TRUE);
        page.setReasonable(Boolean.FALSE);
        List<UserKeywordsDO> list = userKeywordsDAO.selectByExample(this.convertListExample(pagerCondition));
        //结果集设置
        PageListResult<UserKeywordsBO> pageListResult = new PageListResult(UserKeywordsConvert.toBOList(list));
        PageHelperAdaptor.setPageResult(page, pageListResult);
        pageListResult.setPageNum(pagerCondition.getPageNum());
        pageListResult.setPageSize(pagerCondition.getPageSize());
        return pageListResult;
    }
    /**
        *
        * @param userKeywordsQuery
        * @return
        */
    private Example convertExample(UserKeywordsQuery userKeywordsQuery) {
        Example example = new Example(UserKeywordsDO.class);
        Example.Criteria criteria = example.createCriteria();
        if (!ObjectUtils.isEmpty(userKeywordsQuery.getId())) {
            criteria.andEqualTo("id", userKeywordsQuery.getId());
        }
        return example;
    }

    private Example convertListExample(UserKeywordsQuery userKeywordsQuery) {
        Example example = new Example(UserKeywordsListDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag", DelFlagEnum.NOT_DEL.getCode());
        if (!ObjectUtils.isEmpty(userKeywordsQuery.getId())) {
            criteria.andEqualTo("id", userKeywordsQuery.getId());
        }
        if (!ObjectUtils.isEmpty(userKeywordsQuery.getUserId())) {
            criteria.andEqualTo("userId", userKeywordsQuery.getUserId());
        }

        return example;
    }

}
