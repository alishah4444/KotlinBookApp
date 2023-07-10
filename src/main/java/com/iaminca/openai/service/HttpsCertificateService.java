package com.iaminca.openai.service;

import com.iaminca.openai.common.model.PageListResult;
import com.iaminca.openai.query.HttpsCertificateQuery;
import com.iaminca.openai.service.bo.HttpsCertificateBO;

import java.util.List;
/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-07-10 14:20:08
 */
public interface HttpsCertificateService {

    /**
	 * 添加数据
	 * @param
	 * @return
	 */
    int add(HttpsCertificateBO httpsCertificateBO);

    /**
     * 修改数据
     * @param
     * @return
     */
    int update(HttpsCertificateBO httpsCertificateBO);


    /**
     * query
     * @param query
     * @return
     */
    List<HttpsCertificateBO> findList(HttpsCertificateQuery query);

    PageListResult<HttpsCertificateBO> findPage(HttpsCertificateQuery query);
}
