package com.iaminca.openai.web.controller;

import com.iaminca.openai.common.Constants;
import com.iaminca.openai.common.ResultModel;
import com.iaminca.openai.handler.PythonInspectionHandler;
import com.iaminca.openai.service.bo.PythonInspectionResponseBO;
import com.iaminca.openai.web.controller.base.UserBaseController;
import com.iaminca.openai.web.dto.InspectionRequestDTO;
import com.iaminca.openai.web.dto.InspectionResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequiredArgsConstructor
@RequestMapping("/common")
@Slf4j
public class CommoneController extends UserBaseController {

    @Resource
    private PythonInspectionHandler pythonInspectionHandler;


    @PostMapping("/downloadFilw")
    public ResultModel applyKey(@RequestHeader(name = "token")String token) {
        getUserID(token);
        return new ResultModel(Constants.WORDPRESS_FILE_URL);
    }

    @PostMapping("/downloadSQLFilw")
    public ResultModel applyKey(@RequestHeader(name = "token")String token) {
        getUserID(token);
        return new ResultModel(Constants.WORDPRESS_SQL_FILE_URL);
    }


}
