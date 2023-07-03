package com.iaminca.openai.handler;

import com.iaminca.openai.OpenaiApplicationTests;
import com.iaminca.openai.common.Constants;
import com.iaminca.openai.service.bo.PythonInspectionResponseBO;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * @program: auth
 * @description: AdminHandleTest
 * @create: 2023-05-13 00:58
 **/
public class PythonHandlerTest extends OpenaiApplicationTests {
    @Resource
    private PythonInspectionHandler pythonInspectionHandler;


    @Test
    public void inspection() {
        String siteUrl= "https://www.twoapi.com";
        PythonInspectionResponseBO inspection = pythonInspectionHandler.inspection(siteUrl);
        System.out.println("DONE." + Constants.GSON.toJson(inspection));
    }

}
