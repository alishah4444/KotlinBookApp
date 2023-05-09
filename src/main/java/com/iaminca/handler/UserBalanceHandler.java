package com.iaminca.handler;

import com.iaminca.common.Constants;
import com.iaminca.common.ErrorCode;
import com.iaminca.exception.BusinessException;
import com.iaminca.query.UserBalanceQuery;
import com.iaminca.query.UserKeyQuery;
import com.iaminca.service.UserBalanceService;
import com.iaminca.service.UserKeyService;
import com.iaminca.service.bo.UserBalanceBO;
import com.iaminca.service.bo.UserKeyBO;
import com.iaminca.utils.KeyUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

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
public class UserBalanceHandler {

    private static final Integer NEW_USER_BALANCE_TOKENS = 1000;

    @Resource
    private UserBalanceService userBalanceService;

    public void addNewUserBalance(Long userID){
        UserBalanceBO userBalanceBO = new UserBalanceBO();
        log.info("Add UserBalanceBO: {}", Constants.GSON.toJson(userBalanceBO));
        if(ObjectUtils.isEmpty(userBalanceBO) || ObjectUtils.isEmpty(userBalanceBO.getUserId())){
            throw new BusinessException(ErrorCode.PARAM_IS_ERROR);
        }
        userBalanceBO.setUserId(userID);
        userBalanceBO.setUserBalance(NEW_USER_BALANCE_TOKENS);
        userBalanceService.add(userBalanceBO);
    }

    public UserBalanceBO findUserBalance(UserBalanceQuery query){
        List<UserBalanceBO> list = userBalanceService.findList(query);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }


}
