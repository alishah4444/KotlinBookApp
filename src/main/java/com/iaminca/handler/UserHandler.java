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
import com.iaminca.utils.RedisKeyUtil;
import com.iaminca.utils.sms.SendSmsBO;
import com.iaminca.utils.sms.SmsBusinessEnum;
import com.iaminca.utils.sms.SmsSendResponse;
import com.iaminca.utils.sms.SmsSendUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    private StringRedisTemplate stringRedisTemplate;
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
//        String randomNumberString = CodeUtil.getRandomNumberString();
        String randomNumberString = "111111";
        SendSmsBO sendSmsBO=new SendSmsBO();
        sendSmsBO.setCode(randomNumberString);
        SmsSendUtil smsSendUtil=new SmsSendUtil();
        smsSendUtil.sendSms(userBO.getUserPhone(), SmsBusinessEnum.PHONE_REGISTER,sendSmsBO);
        // Save in Redis as Cache.

        stringRedisTemplate.opsForValue().set(RedisKeyUtil.registerCodeKey(userBO.getUserPhone()),randomNumberString,10, TimeUnit.MINUTES);
    }


    public String chekVerificationCode(UserRegisterBO userRegisterBO){
        UserQuery query = new UserQuery();
        query.setUserPhone(userRegisterBO.getUserPhone());
        List<UserBO> list = userService.findList(query);
        if(CollectionUtils.isEmpty(list)){
            list = new ArrayList<>();
            UserBO userBO = new UserBO();
            userBO.setUserPhone(userRegisterBO.getUserPhone());
            userBO.setUserName(userBO.getUserPhone());
            userBO.setUserType(UserTypeEnum.USER.getCode());
            userBO.setUserChatLimitation(CHAT_LIMITATION);
            userBO.setUserLengthLimitation(LENGTH_LIMITATION);
            log.info("Add UserBO: {}", Constants.GSON.toJson(userBO));
            userService.add(userBO);
            list.add(userBO);
        }
        UserBO userBO = list.get(0);
        //TODO Check Verification Code
        String verificationCodeCache = stringRedisTemplate.opsForValue().get(RedisKeyUtil.registerCodeKey(userBO.getUserPhone()));
        if(StringUtils.isEmpty(verificationCodeCache) || !verificationCodeCache.equals(userRegisterBO.getVerificationCode())){
            //Checked Unsuccessfully
            throw new BusinessException(ErrorCode.USER_VERIFICATION_CODE_ERROR);
        }
        stringRedisTemplate.delete(RedisKeyUtil.registerCodeKey(userBO.getUserPhone()));
        //Save the token and information to Cache
        String token = CodeUtil.getUpperUUID();
        stringRedisTemplate.opsForValue().set(RedisKeyUtil.userInfoKey(token), Constants.GSON.toJson(userBO));
        return token;
    }


}
