package com.iaminca.handler;

import com.iaminca.common.Constants;
import com.iaminca.common.UserTypeEnum;
import com.iaminca.service.UserKeyService;
import com.iaminca.service.UserService;
import com.iaminca.service.bo.UserBO;
import com.iaminca.service.bo.UserKeyBO;
import com.iaminca.utils.KeyUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Random;

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
public class UserKeyHandler {

    private static final Integer CHAT_LIMITATION = 2;
    private static final Integer LENGTH_LIMITATION = 200;

    @Resource
    private UserKeyService userKeyService;

    public void addUserKey(UserKeyBO userKeyBO){
        log.info("Add UserKeyBO: {}", Constants.GSON.toJson(userKeyBO));
        if(ObjectUtils.isEmpty(userKeyBO) || ObjectUtils.isEmpty(userKeyBO.getUserId())){
            log.info("No User ID");
            return;
        }
        userKeyBO.setUserKey("sk-"+KeyUtil.openaiKey(48));
        userKeyBO.setUserChatLimitation(CHAT_LIMITATION);
        userKeyBO.setUserLengthLimitation(LENGTH_LIMITATION);
        userKeyService.add(userKeyBO);
    }


}
