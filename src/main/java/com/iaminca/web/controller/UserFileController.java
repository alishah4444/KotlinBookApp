package com.iaminca.web.controller;

import com.iaminca.common.ErrorCode;
import com.iaminca.common.ResultModel;
import com.iaminca.exception.BusinessException;
import com.iaminca.handler.UserHandler;
import com.iaminca.handler.UserKeyHandler;
import com.iaminca.service.StorageService;
import com.iaminca.service.bo.UserRegisterBO;
import com.iaminca.web.controller.base.UserBaseController;
import com.iaminca.web.dto.UserRegisterDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userFile")
@Slf4j
public class UserFileController extends UserBaseController {

    @Resource
    private UserHandler userHandler;
    @Resource
    private UserKeyHandler userKeyHandler;
    @Resource
    private StorageService storageService;

    @PostMapping("/upload2")
    public ResultModel upload(@RequestBody  UserRegisterDTO userRegisterDTO) {
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

    @PostMapping("/upload")
    public String handleFileUpload( @RequestPart("file") FilePart file) {

        String store = storageService.store(file);
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

}
