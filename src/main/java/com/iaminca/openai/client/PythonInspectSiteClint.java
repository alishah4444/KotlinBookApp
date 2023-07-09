package com.iaminca.openai.client;

import com.iaminca.openai.common.Constants;
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
        String execute = PythonExecutor.execute(url,siteUrl);
        log.info("execute : {}",execute);
        return Constants.GSON.fromJson(execute, PythonInspectionResponseBO.class);
    }
}
