package com.iaminca.web.controller;

import com.iaminca.common.ErrorCode;
import com.iaminca.common.ResultModel;
import com.iaminca.common.model.PageListResult;
import com.iaminca.exception.BusinessException;
import com.iaminca.handler.UserHandler;
import com.iaminca.handler.UserKeyHandler;
import com.iaminca.query.UserKeyQuery;
import com.iaminca.service.bo.UserBO;
import com.iaminca.service.bo.UserKeyBO;
import com.iaminca.service.bo.UserRegisterBO;
import com.iaminca.web.controller.base.UserBaseController;
import com.iaminca.web.convert.UserKeyConvertDTO;
import com.iaminca.web.dto.UserKeyDTO;
import com.iaminca.web.dto.UserRegisterDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController extends UserBaseController {

    @Resource
    private UserHandler userHandler;
    @Resource
    private UserKeyHandler userKeyHandler;

    @PostMapping("/register")
    public ResultModel register(@RequestBody  UserRegisterDTO userRegisterDTO) {
        if(ObjectUtils.isEmpty(userRegisterDTO) || ObjectUtils.isEmpty(userRegisterDTO.getUserPhone())||
                ObjectUtils.isEmpty(userRegisterDTO.getVerificationCode())){
            throw new BusinessException(ErrorCode.PARAM_IS_ERROR);
        }
        UserRegisterBO userRegisterBO = new UserRegisterBO();
        userRegisterBO.setUserPhone(userRegisterDTO.getUserPhone());
        userRegisterBO.setVerificationCode(userRegisterDTO.getVerificationCode());
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
    public ResultModel applyKey(@RequestHeader(name = "token")String token) {
        UserKeyBO userKeyBO = new UserKeyBO();
        userKeyBO.setUserId(getUserID(token));
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
    public ResultModel selectKeyPage(@RequestHeader(name = "token")String token,UserKeyQuery query) {
//        UserKeyQuery query = new UserKeyQuery();
        query.setUserId(getUserID(token));
        PageListResult<UserKeyBO> userKeyPage = userKeyHandler.findUserKeyPage(query);
        List<UserKeyDTO> userKeyDTOS = UserKeyConvertDTO.toDTOList(userKeyPage.getList());
        PageListResult<UserKeyDTO> page = new PageListResult<>(userKeyDTOS,userKeyPage.getPageNum(),userKeyPage.getPageSize(),userKeyPage.getTotal());
        return new ResultModel(page);
    }

}
