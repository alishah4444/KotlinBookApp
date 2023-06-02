package com.iaminca.service.impl;

import com.github.pagehelper.Page;
import com.iaminca.common.Constants;
import com.iaminca.common.DelFlagEnum;
import com.iaminca.common.model.PageHelperAdaptor;
import com.iaminca.common.model.PageListResult;
import com.iaminca.dal.dao.UserPostsDAO;
import com.iaminca.dal.dataobject.UserPostsDO;
import com.iaminca.dal.dataobject.UserPostsListDO;
import com.iaminca.query.UserPostsQuery;
import com.iaminca.service.UserPostsService;
import com.iaminca.service.bo.UserPostsBO;
import com.iaminca.service.covert.UserPostsConvert;
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
public class UserPostsServiceImpl implements UserPostsService {

	@Resource
	private UserPostsDAO userPostsDAO;


    @Override
    public int add(UserPostsBO userPostsBO){
        userPostsBO.setId(null);
        userPostsBO.setDelFlag(DelFlagEnum.NOT_DEL.getCode());
        userPostsBO.setCreateTime(new Date());
        userPostsBO.setUpdateTime( userPostsBO.getCreateTime());
        UserPostsDO userPostsDO = UserPostsConvert.toDO(userPostsBO);
		return userPostsDAO.insert(userPostsDO);
    }

    @Override
    public int update(UserPostsBO userPostsBO){
		UserPostsDO userPostsDO = UserPostsConvert.toDO(userPostsBO);
        return userPostsDAO.updateByPrimaryKeySelective(userPostsDO);
    }

    @Override
    public List<UserPostsBO> findList(UserPostsQuery query){
        List<UserPostsDO> listByQuery = userPostsDAO.selectByExample(this.convertListExample(query));
        return UserPostsConvert.toBOList(listByQuery);
    }

    @Override
    public UserPostsBO findOne(UserPostsQuery query) {
        UserPostsDO userPostsDO = userPostsDAO.selectOneByExample(this.convertExample(query));
        return UserPostsConvert.toBO(userPostsDO);
    }

    @Override
    public PageListResult<UserPostsBO> findPage(UserPostsQuery pagerCondition){
        //分页页码设置
        pagerCondition.setOrderBy(Constants.ORDER_BY);
        Page page = PageHelperAdaptor.preparePage(pagerCondition, Boolean.TRUE);
        page.setReasonable(Boolean.FALSE);
        List<UserPostsDO> list = userPostsDAO.selectByExample(this.convertListExample(pagerCondition));
        //结果集设置
        PageListResult<UserPostsBO> pageListResult = new PageListResult(UserPostsConvert.toBOList(list));
        PageHelperAdaptor.setPageResult(page, pageListResult);
        pageListResult.setPageNum(pagerCondition.getPageNum());
        pageListResult.setPageSize(pagerCondition.getPageSize());
        return pageListResult;
    }
    /**
        *
        * @param userPostsQuery
        * @return
        */
    private Example convertExample(UserPostsQuery userPostsQuery) {
        Example example = new Example(UserPostsDO.class);
        Example.Criteria criteria = example.createCriteria();
        if (!ObjectUtils.isEmpty(userPostsQuery.getId())) {
            criteria.andEqualTo("id", userPostsQuery.getId());
        }
        return example;
    }

    /**
     *
     * @param userPostsQuery
     * @return
     */
    private Example convertListExample(UserPostsQuery userPostsQuery) {
        Example example = new Example(UserPostsListDO.class);
        Example.Criteria criteria = example.createCriteria();
        if (!ObjectUtils.isEmpty(userPostsQuery.getId())) {
            criteria.andEqualTo("id", userPostsQuery.getId());
        }
        return example;
    }

}
