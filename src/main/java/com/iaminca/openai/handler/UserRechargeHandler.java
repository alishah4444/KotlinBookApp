package com.iaminca.openai.handler;

import com.iaminca.openai.common.Constants;
import com.iaminca.openai.common.ErrorCode;
import com.iaminca.openai.common.RechargeStatusEnum;
import com.iaminca.openai.common.model.PageListResult;
import com.iaminca.openai.exception.BusinessException;
import com.iaminca.openai.query.UserRechargeQuery;
import com.iaminca.openai.service.UserRechargeService;
import com.iaminca.openai.service.bo.UserRechargeBO;
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
public class UserRechargeHandler {

    @Resource
    private UserRechargeService userRechargeService;

    public void submitUserRecharge(UserRechargeBO userRechargeBO){
        log.info("Add userRechargeBO: {}", Constants.GSON.toJson(userRechargeBO));
        if(ObjectUtils.isEmpty(userRechargeBO) || ObjectUtils.isEmpty(userRechargeBO.getUserId())
                || ObjectUtils.isEmpty(userRechargeBO.getRechargeMoney())){
            log.info("No User ID Or Money");
           throw new BusinessException(ErrorCode.PARAM_IS_ERROR);
        }

        userRechargeBO.setRechargeStatus(RechargeStatusEnum.WAITING.getCode());
        userRechargeService.add(userRechargeBO);
    }

    public PageListResult<UserRechargeBO> findUserRechargePage(UserRechargeQuery query){
        PageListResult<UserRechargeBO> pageListResult = userRechargeService.findPage(query);
        return pageListResult;
    }

}
