package com.iaminca.handler;

import com.iaminca.common.Constants;
import com.iaminca.common.ErrorCode;
import com.iaminca.common.UserTypeEnum;
import com.iaminca.exception.BusinessException;
import com.iaminca.query.UserQuery;
import com.iaminca.service.UserService;
import com.iaminca.service.bo.UserBO;
import com.iaminca.service.bo.UserRegisterBO;
import com.iaminca.utils.CodeUtil;
import com.iaminca.utils.sms.SendSmsBO;
import com.iaminca.utils.sms.SmsBusinessEnum;
import com.iaminca.utils.sms.SmsSendResponse;
import com.iaminca.utils.sms.SmsSendUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
public class UserHandler {

    private static final Integer CHAT_LIMITATION = 2;
    private static final Integer LENGTH_LIMITATION = 200;

    @Autowired
    private RedisTemplate<String, String> template;
    @Resource
    private UserService userService;

    public void addUser(UserBO userBO){
        UserQuery query = new UserQuery();
        query.setUserPhone(userBO.getUserPhone());
        List<UserBO> list = userService.findList(query);
        if(CollectionUtils.isEmpty(list)){
            userBO.setUserName(userBO.getUserPhone());
            userBO.setUserType(UserTypeEnum.USER.getCode());
            userBO.setUserChatLimitation(CHAT_LIMITATION);
            userBO.setUserLengthLimitation(LENGTH_LIMITATION);
            log.info("Add UserBO: {}", Constants.GSON.toJson(userBO));
            userService.add(userBO);
        }
        //TODO Send Verification Code
        String randomNumberString = CodeUtil.getRandomNumberString();
        SendSmsBO sendSmsBO=new SendSmsBO();
        sendSmsBO.setCode(randomNumberString);
        SmsSendUtil smsSendUtil=new SmsSendUtil();
        smsSendUtil.sendSms(userBO.getUserPhone(), SmsBusinessEnum.PHONE_REGISTER,sendSmsBO);
        // Save in Redis as Cache.
        template.opsForValue().set(userBO.getUserPhone(),randomNumberString);
    }


    public void chekVerificationCode(UserRegisterBO userRegisterBO){
        UserQuery query = new UserQuery();
        query.setUserPhone(userRegisterBO.getUserPhone());
        List<UserBO> list = userService.findList(query);
        if(CollectionUtils.isEmpty(list)){
            UserBO userBO = new UserBO();
            userBO.setUserPhone(userRegisterBO.getUserPhone());
            userBO.setUserName(userBO.getUserPhone());
            userBO.setUserType(UserTypeEnum.USER.getCode());
            userBO.setUserChatLimitation(CHAT_LIMITATION);
            userBO.setUserLengthLimitation(LENGTH_LIMITATION);
            log.info("Add UserBO: {}", Constants.GSON.toJson(userBO));
            userService.add(userBO);
        }
        //TODO Check Verification Code
        String verificationCodeCache = template.opsForValue().get(userRegisterBO.getUserPhone());
        if(StringUtils.isEmpty(verificationCodeCache) && !verificationCodeCache.equals(userRegisterBO.getVerificationCode())){
            //Checked Unsuccessfully
            throw new BusinessException(ErrorCode.PARAM_IS_ERROR);
        }
        //Save the token and information to Cacge

    }


}
