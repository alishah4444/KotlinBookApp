package com.iaminca.openai.service.impl;

import com.github.pagehelper.Page;
import com.iaminca.openai.common.Constants;
import com.iaminca.openai.common.DelFlagEnum;
import com.iaminca.openai.common.model.PageHelperAdaptor;
import com.iaminca.openai.common.model.PageListResult;
import com.iaminca.openai.dal.dao.HttpsCertificateDAO;
import com.iaminca.openai.dal.dataobject.HttpsCertificateDO;
import com.iaminca.openai.query.HttpsCertificateQuery;
import com.iaminca.openai.service.HttpsCertificateService;
import com.iaminca.openai.service.bo.HttpsCertificateBO;
import com.iaminca.openai.service.covert.HttpsCertificateConvert;
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
 * @date 2023-07-10 14:20:08
 */
@Service
public class HttpsCertificateServiceImpl implements HttpsCertificateService {

	@Resource
	private HttpsCertificateDAO httpsCertificateDAO;


    @Override
    public int add(HttpsCertificateBO httpsCertificateBO){
        httpsCertificateBO.setId(null);
        httpsCertificateBO.setDelFlag(DelFlagEnum.NOT_DEL.getCode());
        httpsCertificateBO.setCreateTime(new Date());
        httpsCertificateBO.setUpdateTime( httpsCertificateBO.getCreateTime());
        HttpsCertificateDO httpsCertificateDO = HttpsCertificateConvert.toDO(httpsCertificateBO);
		return httpsCertificateDAO.insert(httpsCertificateDO);
    }

    @Override
    public int update(HttpsCertificateBO httpsCertificateBO){
		HttpsCertificateDO httpsCertificateDO = HttpsCertificateConvert.toDO(httpsCertificateBO);
        return httpsCertificateDAO.updateByPrimaryKeySelective(httpsCertificateDO);
    }

    @Override
    public List<HttpsCertificateBO> findList(HttpsCertificateQuery query){
        List<HttpsCertificateDO> listByQuery = httpsCertificateDAO.selectByExample(this.convertExample(query));
        return HttpsCertificateConvert.toBOList(listByQuery);
    }

    @Override
    public PageListResult<HttpsCertificateBO> findPage(HttpsCertificateQuery pagerCondition){
        //分页页码设置
        pagerCondition.setOrderBy(Constants.ORDER_BY);
        Page page = PageHelperAdaptor.preparePage(pagerCondition, Boolean.TRUE);
        page.setReasonable(Boolean.FALSE);
        List<HttpsCertificateDO> list = httpsCertificateDAO.selectByExample(this.convertExample(pagerCondition));
        //结果集设置
        PageListResult<HttpsCertificateBO> pageListResult = new PageListResult(HttpsCertificateConvert.toBOList(list));
        PageHelperAdaptor.setPageResult(page, pageListResult);
        pageListResult.setPageNum(pagerCondition.getPageNum());
        pageListResult.setPageSize(pagerCondition.getPageSize());
        return pageListResult;
    }
    /**
        *
        * @param httpsCertificateQuery
        * @return
        */
    private Example convertExample(HttpsCertificateQuery httpsCertificateQuery) {
        Example example = new Example(HttpsCertificateDO.class);
        Example.Criteria criteria = example.createCriteria();
        if (!ObjectUtils.isEmpty(httpsCertificateQuery.getId())) {
            criteria.andEqualTo("id", httpsCertificateQuery.getId());
        }
        return example;
    }

}
