package com.iaminca.handler;

import com.iaminca.common.Constants;
import com.iaminca.common.ErrorCode;
import com.iaminca.exception.BusinessException;
import com.iaminca.service.UserApplyGpt4Service;
import com.iaminca.service.bo.UserApplyGpt4BO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:22
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class UserGPT4Handler {

    @Resource
    private UserApplyGpt4Service userApplyGpt4Service;

    public void applyGPT4(UserApplyGpt4BO userApplyGpt4BO){
        log.info("Add applyGPT4: {}", Constants.GSON.toJson(userApplyGpt4BO));
        if(ObjectUtils.isEmpty(userApplyGpt4BO) || ObjectUtils.isEmpty(userApplyGpt4BO.getUserId())){
            log.info("No User ID");
           throw new BusinessException(ErrorCode.PARAM_IS_ERROR);
        }
        userApplyGpt4Service.add(userApplyGpt4BO);
    }

}
