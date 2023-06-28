package com.iaminca.openai.handler;

import com.iaminca.openai.common.ErrorCode;
import com.iaminca.openai.exception.BusinessException;
import com.iaminca.openai.query.UserBalanceQuery;
import com.iaminca.openai.service.UserBalanceService;
import com.iaminca.openai.service.bo.UserBalanceBO;
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

        log.info("Add UserBalanceBO: {}", userID);
        if(ObjectUtils.isEmpty(userID)){
            throw new BusinessException(ErrorCode.PARAM_IS_ERROR);
        }
        UserBalanceBO userBalanceBO = new UserBalanceBO();
        userBalanceBO.setUserId(userID);
        userBalanceBO.setUserBalance(NEW_USER_BALANCE_TOKENS);
        userBalanceService.add(userBalanceBO);
    }

    public UserBalanceBO findUserBalance(Long userId){
        UserBalanceQuery query = new UserBalanceQuery();
        query.setUserId(userId);
        List<UserBalanceBO> list = userBalanceService.findList(query);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }


}
