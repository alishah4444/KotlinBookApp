package com.iaminca.openai.client;

import com.iaminca.openai.common.Constants;
import com.iaminca.openai.service.bo.HttpsCertificatePythonBO;
import com.iaminca.openai.service.bo.PythonInspectionResponseBO;
import com.iaminca.openai.utils.python.PythonExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PythonInspectSiteClint {

    /**
     * Inject
     * @param url
     */
    public PythonInspectionResponseBO inspection(String url,String siteUrl){
        log.info("execute start: {},{}",url,siteUrl);
        String execute = PythonExecutor.execute(url,siteUrl);
        log.info("execute : {}",execute);
        return Constants.GSON.fromJson(execute, PythonInspectionResponseBO.class);
    }
    /**
     * Check CNAME
     * @param siteUrl
     */
    public HttpsCertificatePythonBO checkCname(String url,String siteUrl){
        log.info("execute start: {},{}",url,siteUrl);
        String execute = PythonExecutor.execute(url,siteUrl);
        log.info("checkCname : {}",execute);
        return Constants.GSON.fromJson(execute, HttpsCertificatePythonBO.class);
    }

    /**
     * Check CNAME
     * @param siteUrl
     */
    public HttpsCertificatePythonBO applyCertificate(String url, String siteUrl){
        log.info("execute start: {},{}",url,siteUrl);
        String execute = PythonExecutor.execute(url,siteUrl);
        log.info("applyCertificate : {}",execute);
        return Constants.GSON.fromJson(execute, HttpsCertificatePythonBO.class);
    }
}
