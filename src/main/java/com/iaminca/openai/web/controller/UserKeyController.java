package com.iaminca.openai.web.controller;

import com.iaminca.openai.common.ResultModel;
import com.iaminca.openai.common.model.PageListResult;
import com.iaminca.openai.handler.UserGPT4Handler;
import com.iaminca.openai.handler.UserKeyHandler;
import com.iaminca.openai.web.dto.UserApplyGpt4DTO;
import com.iaminca.openai.query.UserKeyQuery;
import com.iaminca.openai.service.bo.UserKeyBO;
import com.iaminca.openai.web.controller.base.UserBaseController;
import com.iaminca.openai.web.convert.UserApplyGpt4DTOConvert;
import com.iaminca.openai.web.convert.UserKeyConvertDTO;
import com.iaminca.openai.web.dto.UserKeyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserKeyController extends UserBaseController {

    @Resource
    private UserGPT4Handler userGPT4Handler;
    @Resource
    private UserKeyHandler userKeyHandler;


    @PostMapping("/applyKey")
    public ResultModel applyKey(@RequestHeader(name = "token")String token,@RequestBody UserKeyDTO userKeyDTO) {
        UserKeyBO userKeyBO = new UserKeyBO();
        userKeyBO.setUserId(getUserID(token));
        userKeyBO.setName(userKeyDTO.getName());
        String gptKey = userKeyHandler.addUserKey(userKeyBO);
        return new ResultModel(gptKey);
    }

    @PostMapping("/selectKey")
    public ResultModel selectKey(@RequestHeader(name = "token",required = false)String token) {
        UserKeyQuery query = new UserKeyQuery();
        query.setUserId(getUserID(token));
        List<UserKeyBO> userKeyList = userKeyHandler.findUserKeyList(query);
        List<UserKeyDTO> userKeyDTOS = UserKeyConvertDTO.toDTOList(userKeyList);
        return new ResultModel(userKeyDTOS);
    }


    @GetMapping("/selectKeyPage")
    public ResultModel selectKeyPage(@RequestHeader(name = "token",required = false)String token,UserKeyQuery query) {
//        UserKeyQuery query = new UserKeyQuery();
        query.setUserId(getUserID(token));
        PageListResult<UserKeyBO> userKeyPage = userKeyHandler.findUserKeyPage(query);
        List<UserKeyDTO> userKeyDTOS = UserKeyConvertDTO.toDTOList(userKeyPage.getList());
        PageListResult<UserKeyDTO> page = new PageListResult<>(userKeyDTOS,userKeyPage.getPageNum(),userKeyPage.getPageSize(),userKeyPage.getTotal());
        return new ResultModel(page);
    }


    @PostMapping("/deleteKey")
    public ResultModel deleteKey(@RequestBody UserKeyDTO userKeyDTO, @RequestHeader(name = "token")String token) {
        getUserID(token);
        userKeyHandler.deleteUserKey(userKeyDTO.getId());
        return new ResultModel();
    }

    @PostMapping("/updateKeyName")
    public ResultModel updateKeyName(@RequestBody UserKeyDTO userKeyDTO, @RequestHeader(name = "token")String token) {
        userKeyDTO.setUserId(getUserID(token));
        userKeyHandler.updateById(UserKeyConvertDTO.toBO(userKeyDTO));
        return new ResultModel();
    }

    @PostMapping("/applyGPT4")
    public ResultModel applyGPT4(@RequestBody UserApplyGpt4DTO userApplyGpt4DTO, @RequestHeader(name = "token")String token) {
        userApplyGpt4DTO.setUserId(getUserID(token));
        userGPT4Handler.applyGPT4(UserApplyGpt4DTOConvert.toBO(userApplyGpt4DTO));
        return new ResultModel();
    }

}
