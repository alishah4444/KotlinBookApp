package com.iaminca.openai.handler;

import com.iaminca.openai.client.PythonInspectSiteClint;
import com.iaminca.openai.common.Constants;
import com.iaminca.openai.common.ErrorCode;
import com.iaminca.openai.exception.BusinessException;
import com.iaminca.openai.service.bo.PythonInspectionResponseBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
public class PythonInspectionHandler {
    @Value("${python.url.root}")
    private String pythonUrlRoot;

    @Resource
    private PythonInspectSiteClint pythonInspectSiteClint;

    public PythonInspectionResponseBO inspection(String siteUrl){
        if(StringUtils.isEmpty(siteUrl)){
            throw new BusinessException(ErrorCode.PARAM_IS_ERROR);
        }
        siteUrl = "https://" + siteUrl;
        String url = pythonUrlRoot+ Constants.PYTHON_INSPECTION_URL;
//        String siteUrl= "https://www.twoapi.com";
        PythonInspectionResponseBO inspection = pythonInspectSiteClint.inspection(url, siteUrl);
        if(inspection == null){
            throw new BusinessException(ErrorCode.SITE_URL_ERROR);
        }
       return inspection;
    }

}
