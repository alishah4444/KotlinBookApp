package com.iaminca.openai.service.impl;

import com.github.pagehelper.Page;
import com.iaminca.openai.common.Constants;
import com.iaminca.openai.common.DelFlagEnum;
import com.iaminca.openai.common.model.PageHelperAdaptor;
import com.iaminca.openai.common.model.PageListResult;
import com.iaminca.openai.dal.dao.UserTaskInfoDAO;
import com.iaminca.openai.dal.dataobject.UserTaskInfoDO;
import com.iaminca.openai.query.UserTaskInfoQuery;
import com.iaminca.openai.service.UserTaskInfoService;
import com.iaminca.openai.service.bo.UserTaskInfoBO;
import com.iaminca.openai.service.covert.UserTaskInfoConvert;
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
public class UserTaskInfoServiceImpl implements UserTaskInfoService {

	@Resource
	private UserTaskInfoDAO userTaskInfoDAO;


    @Override
    public int add(UserTaskInfoBO userTaskInfoBO){
        userTaskInfoBO.setId(null);
        userTaskInfoBO.setDelFlag(DelFlagEnum.NOT_DEL.getCode());
        userTaskInfoBO.setCreateTime(new Date());
        userTaskInfoBO.setUpdateTime( userTaskInfoBO.getCreateTime());
        UserTaskInfoDO userTaskInfoDO = UserTaskInfoConvert.toDO(userTaskInfoBO);
		return userTaskInfoDAO.insert(userTaskInfoDO);
    }

    @Override
    public int update(UserTaskInfoBO userTaskInfoBO){
		UserTaskInfoDO userTaskInfoDO = UserTaskInfoConvert.toDO(userTaskInfoBO);
        return userTaskInfoDAO.updateByPrimaryKeySelective(userTaskInfoDO);
    }

    @Override
    public List<UserTaskInfoBO> findList(UserTaskInfoQuery query){
        List<UserTaskInfoDO> listByQuery = userTaskInfoDAO.selectByExample(this.convertExample(query));
        return UserTaskInfoConvert.toBOList(listByQuery);
    }

    @Override
    public UserTaskInfoBO findOne(UserTaskInfoQuery query) {
        UserTaskInfoDO userTaskInfoDO = userTaskInfoDAO.selectOneByExample(this.convertExample(query));
        return UserTaskInfoConvert.toBO(userTaskInfoDO);
    }

    @Override
    public PageListResult<UserTaskInfoBO> findPage(UserTaskInfoQuery pagerCondition){
        //分页页码设置
        pagerCondition.setOrderBy(Constants.ORDER_BY);
        Page page = PageHelperAdaptor.preparePage(pagerCondition, Boolean.TRUE);
        page.setReasonable(Boolean.FALSE);
        List<UserTaskInfoDO> list = userTaskInfoDAO.selectByExample(this.convertExample(pagerCondition));
        //结果集设置
        PageListResult<UserTaskInfoBO> pageListResult = new PageListResult(UserTaskInfoConvert.toBOList(list));
        PageHelperAdaptor.setPageResult(page, pageListResult);
        pageListResult.setPageNum(pagerCondition.getPageNum());
        pageListResult.setPageSize(pagerCondition.getPageSize());
        return pageListResult;
    }
    /**
        *
        * @param userTaskInfoQuery
        * @return
        */
    private Example convertExample(UserTaskInfoQuery userTaskInfoQuery) {
        Example example = new Example(UserTaskInfoDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag", DelFlagEnum.NOT_DEL.getCode());
        if (!ObjectUtils.isEmpty(userTaskInfoQuery.getId())) {
            criteria.andEqualTo("id", userTaskInfoQuery.getId());
        }
        return example;
    }

}
