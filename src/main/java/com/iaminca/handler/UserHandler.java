package com.iaminca.handler;

import com.iaminca.common.Constants;
import com.iaminca.common.UserTypeEnum;
import com.iaminca.service.UserService;
import com.iaminca.service.bo.UserBO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
public class UserHandler {

    private static final Integer CHAT_LIMITATION = 2;
    private static final Integer LENGTH_LIMITATION = 200;

    @Resource
    private UserService userService;

    public void addUser(UserBO userBO){
        userBO.setUserName(userBO.getUserPhone());
        userBO.setUserType(UserTypeEnum.USER.getCode());
        userBO.setUserChatLimitation(CHAT_LIMITATION);
        userBO.setUserLengthLimitation(LENGTH_LIMITATION);
        log.info("Add UserBO: {}", Constants.GSON.toJson(userBO));
        userService.add(userBO);
    }

}
