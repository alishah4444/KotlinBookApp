package com.iaminca.openai.web.controller;

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
@RequestMapping("/inspection")
@Slf4j
public class PythonController extends UserBaseController {

    @Resource
    private PythonInspectionHandler pythonInspectionHandler;


    @PostMapping("/checkSite")
    public ResultModel checkSite(@RequestHeader(name = "token")String token,@RequestBody InspectionRequestDTO inspectionRequestDTO) {
        PythonInspectionResponseBO inspection = pythonInspectionHandler.inspection(inspectionRequestDTO.getSiteUrl());
        InspectionResponseDTO responseDTO = new InspectionResponseDTO();
        responseDTO.setTotalTime(inspection.getTotalTime());
        return new ResultModel(responseDTO);
    }

}
