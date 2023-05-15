package com.iaminca.web.controller;

import com.iaminca.common.ErrorCode;
import com.iaminca.common.ResultModel;
import com.iaminca.common.model.PageListResult;
import com.iaminca.exception.BusinessException;
import com.iaminca.handler.UserHandler;
import com.iaminca.handler.UserKeyHandler;
import com.iaminca.query.UserKeyQuery;
import com.iaminca.query.UserQuery;
import com.iaminca.service.bo.UserBO;
import com.iaminca.service.bo.UserKeyBO;
import com.iaminca.service.bo.UserLoginBO;
import com.iaminca.service.bo.UserRegisterBO;
import com.iaminca.web.controller.base.UserBaseController;
import com.iaminca.web.convert.UserKeyConvertDTO;
import com.iaminca.web.dto.UserKeyDTO;
import com.iaminca.web.dto.UserLoginDTO;
import com.iaminca.web.dto.UserMessageDTO;
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
                ObjectUtils.isEmpty(userRegisterDTO.getVerificationCode()) || ObjectUtils.isEmpty(userRegisterDTO.getPassword())){
            throw new BusinessException(ErrorCode.PARAM_IS_ERROR);
        }
        UserRegisterBO userRegisterBO = new UserRegisterBO();
        userRegisterBO.setUserPhone(userRegisterDTO.getUserPhone());
        userRegisterBO.setVerificationCode(userRegisterDTO.getVerificationCode());
        userRegisterBO.setPassword(userRegisterDTO.getPassword());
        String token = userHandler.chekVerificationCode(userRegisterBO);
        return new ResultModel(token);
    }

    //TODO 现在token有重复的，加入新的token之前，要把老的token删除。
    @PostMapping("/login")
    public ResultModel login(@RequestBody UserLoginDTO userLoginDTO) {
        if(ObjectUtils.isEmpty(userLoginDTO) || ObjectUtils.isEmpty(userLoginDTO.getUserPhone())
                ||ObjectUtils.isEmpty(userLoginDTO.getPassword())){
            throw new BusinessException(ErrorCode.PARAM_IS_ERROR);
        }
        UserLoginBO userLoginBO = new UserLoginBO();
        userLoginBO.setUserPhone(userLoginDTO.getUserPhone());
        userLoginBO.setPassword(userLoginDTO.getPassword());
        String token = userHandler.passwordLogin(userLoginBO);
        return new ResultModel(token);
    }

    @PostMapping("/sendCode")
    public ResultModel sendCode(@RequestBody  UserRegisterDTO userRegisterDTO) {
        UserBO userBO = new UserBO();
        userBO.setUserPhone(userRegisterDTO.getUserPhone());
        userHandler.addUser(userBO);
        return new ResultModel();
    }


    @PostMapping("/myMessage")
    public ResultModel myMessage(@RequestHeader(name = "token")String token) {
        UserBO user = getUser(token);
        Long userBalance = userHandler.findUserBalance(user.getId());
        UserMessageDTO userMessageDTO = new UserMessageDTO();
        userMessageDTO.setUserPhone(user.getUserPhone());
        userMessageDTO.setBalance(userBalance);
        return new ResultModel(userMessageDTO);
    }


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


}
