package com.iaminca.openai.web.controller;

import com.iaminca.openai.common.ResultModel;
import com.iaminca.openai.common.model.PageListResult;
import com.iaminca.openai.handler.UserRechargeHandler;
import com.iaminca.openai.query.UserRechargeQuery;
import com.iaminca.openai.service.bo.UserRechargeBO;
import com.iaminca.openai.utils.MoneyUtil;
import com.iaminca.openai.web.convert.UserRechargeConvert;
import com.iaminca.openai.web.controller.base.UserBaseController;
import com.iaminca.openai.web.dto.UserRechargeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserRechargeController extends UserBaseController {

    @Resource
    private UserRechargeHandler userRechargeHandler;

    @PostMapping("/recharge")
    public ResultModel recharge(@RequestHeader(name = "token")String token,@RequestBody UserRechargeDTO userRechargeDTO) {
        UserRechargeBO userRechargeBO = new UserRechargeBO();
        userRechargeBO.setUserId(getUserID(token));
        userRechargeBO.setRechargeMoney(MoneyUtil.getCentUniteMoney(userRechargeDTO.getRechargeMoney()));
        userRechargeBO.setFilePath(userRechargeDTO.getFilePath());
        userRechargeHandler.submitUserRecharge(userRechargeBO);
        return new ResultModel();
    }


    @GetMapping("/selectRecharge")
    public ResultModel selectRecharge(@RequestHeader(name = "token")String token, UserRechargeQuery query) {
//        UserKeyQuery query = new UserKeyQuery();
        query.setUserId(getUserID(token));
        PageListResult<UserRechargeBO> userRechargePage = userRechargeHandler.findUserRechargePage(query);
        List<UserRechargeDTO> userKeyDTOS = UserRechargeConvert.toDTOList(userRechargePage.getList());
        PageListResult<UserRechargeDTO> page = new PageListResult<>(userKeyDTOS,userRechargePage.getPageNum(),userRechargePage.getPageSize(),userRechargePage.getTotal());
        return new ResultModel(page);
    }


}
