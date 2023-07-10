package com.iaminca.openai.handler;

import com.iaminca.openai.common.ErrorCode;
import com.iaminca.openai.exception.BusinessException;
import com.iaminca.openai.service.HttpsCertificateService;
import com.iaminca.openai.service.bo.HttpsCertificateBO;
import com.iaminca.openai.service.bo.HttpsCertificatePythonBO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:22
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class HttpsCertificateHandler {

    @Resource
    private PythonInspectionHandler pythonInspectionHandler;
    @Resource
    private HttpsCertificateService httpsCertificateService;

    public void insert(HttpsCertificateBO httpsCertificateBO){

        if(StringUtils.isEmpty(httpsCertificateBO.getSiteUrl())){
            throw new BusinessException(ErrorCode.PARAM_IS_ERROR);
        }

        HttpsCertificatePythonBO httpsCertificatePythonBO = pythonInspectionHandler.applyCertificate(httpsCertificateBO.getSiteUrl());
        if(httpsCertificatePythonBO == null){
            throw new BusinessException(ErrorCode.SITE_URL_ERROR);
        }


        HttpsCertificateBO insertBO = new HttpsCertificateBO();
        insertBO.setUserId(httpsCertificateBO.getUserId());
        insertBO.setSiteUrl(httpsCertificateBO.getSiteUrl());
        insertBO.setPublicKeyUrl(httpsCertificatePythonBO.getPublicKeyUrl());
        insertBO.setPrivateKeyUrl(httpsCertificatePythonBO.getPrivateKeyUrl());
        httpsCertificateService.add(insertBO);

    }

}
