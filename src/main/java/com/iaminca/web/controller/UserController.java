package com.iaminca.web.controller;

import com.iaminca.common.ResultModel;
import com.iaminca.handler.UserHandler;
import com.iaminca.handler.UserKeyHandler;
import com.iaminca.service.bo.UserBO;
import com.iaminca.service.bo.UserKeyBO;
import com.iaminca.web.dto.UserRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserHandler userHandler;
    @Resource
    private UserKeyHandler userKeyHandler;

    @PostMapping("/register")
    public ResultModel register(@RequestBody  UserRegisterDTO userRegisterDTO) {
        UserBO userBO = new UserBO();
        userBO.setUserPhone(userRegisterDTO.getUserPhone());
        userHandler.addUser(userBO);
        return new ResultModel();
    }

    @PostMapping("/applyKey")
    public ResultModel applyKey() {
        UserKeyBO userKeyBO = new UserKeyBO();
        userKeyBO.setUserId(5000L);
        userKeyHandler.addUserKey(userKeyBO);
        return new ResultModel();
    }

}
