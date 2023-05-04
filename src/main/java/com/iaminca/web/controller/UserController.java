package com.iaminca.web.controller;

import com.iaminca.common.ResultModel;
import com.iaminca.handler.UserHandler;
import com.iaminca.service.bo.UserBO;
import com.iaminca.web.dto.UserRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserHandler userHandler;

    @PostMapping("/register")
    public ResultModel register(UserRegisterDTO userRegisterDTO) {
        UserBO userBO = new UserBO();
        userBO.setUserPhone(userRegisterDTO.getUserPhone());
        userHandler.addUser(userBO);
        return new ResultModel();
    }

}
