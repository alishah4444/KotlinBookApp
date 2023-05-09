package com.iaminca.web.controller;

import com.iaminca.common.ResultModel;
import com.iaminca.handler.UserHandler;
import com.iaminca.handler.UserKeyHandler;
import com.iaminca.service.bo.UserBO;
import com.iaminca.service.bo.UserKeyBO;
import com.iaminca.service.bo.UserRegisterBO;
import com.iaminca.web.controller.base.UserBaseController;
import com.iaminca.web.dto.UserRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController extends UserBaseController {

    @Resource
    private UserHandler userHandler;
    @Resource
    private UserKeyHandler userKeyHandler;

    @PostMapping("/register")
    public ResultModel register(@RequestBody  UserRegisterDTO userRegisterDTO) {
        UserRegisterBO userRegisterBO = new UserRegisterBO();
        userRegisterBO.setUserPhone(userRegisterDTO.getUserPhone());
        String token = userHandler.chekVerificationCode(userRegisterBO);
        return new ResultModel(token);
    }

    @PostMapping("/sendCode")
    public ResultModel sendCode(@RequestBody  UserRegisterDTO userRegisterDTO) {
        UserBO userBO = new UserBO();
        userBO.setUserPhone(userRegisterDTO.getUserPhone());
        userHandler.addUser(userBO);
        return new ResultModel();
    }

    @PostMapping("/applyKey")
    public ResultModel applyKey(@RequestHeader("token")String token) {
        UserKeyBO userKeyBO = new UserKeyBO();
        userKeyBO.setUserId(getUserID(token));
        userKeyHandler.addUserKey(userKeyBO);
        return new ResultModel();
    }

}
