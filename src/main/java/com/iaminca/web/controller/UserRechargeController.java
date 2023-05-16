package com.iaminca.web.controller;

import com.iaminca.common.ResultModel;
import com.iaminca.common.model.PageListResult;
import com.iaminca.handler.UserHandler;
import com.iaminca.handler.UserKeyHandler;
import com.iaminca.handler.UserRechargeHandler;
import com.iaminca.query.UserKeyQuery;
import com.iaminca.query.UserRechargeQuery;
import com.iaminca.service.bo.UserKeyBO;
import com.iaminca.service.bo.UserRechargeBO;
import com.iaminca.utils.MoneyUtil;
import com.iaminca.web.controller.base.UserBaseController;
import com.iaminca.web.convert.UserKeyConvertDTO;
import com.iaminca.web.convert.UserRechargeConvert;
import com.iaminca.web.dto.UserKeyDTO;
import com.iaminca.web.dto.UserRechargeDTO;
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
