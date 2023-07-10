package com.iaminca.openai.web.controller;

import com.iaminca.openai.common.ResultModel;
import com.iaminca.openai.handler.HttpsCertificateHandler;
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
@RequestMapping("/httpsLine")
@Slf4j
public class HttpsCertificateController extends UserBaseController {

    @Resource
    private PythonInspectionHandler pythonInspectionHandler;
    @Resource
    private HttpsCertificateHandler httpsCertificateHandler;



    @PostMapping("/checkCname")
    public ResultModel checkCname(@RequestHeader(name = "token")String token,@RequestBody InspectionRequestDTO inspectionRequestDTO) {
        getUser(token);
        pythonInspectionHandler.checkCname(inspectionRequestDTO.getSiteUrl());
        return new ResultModel();
    }

    @PostMapping("/applyCertificate")
    public ResultModel applyCertificate(@RequestHeader(name = "token")String token,@RequestBody InspectionRequestDTO inspectionRequestDTO) {
        getUser(token);
        pythonInspectionHandler.applyCertificate(inspectionRequestDTO.getSiteUrl());
        return new ResultModel();
    }


}
