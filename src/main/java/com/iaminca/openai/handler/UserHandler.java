package com.iaminca.openai.handler;

import com.iaminca.openai.common.Constants;
import com.iaminca.openai.common.ErrorCode;
import com.iaminca.openai.common.UserTypeEnum;
import com.iaminca.openai.exception.BusinessException;
import com.iaminca.openai.query.UserQuery;
import com.iaminca.openai.service.UserService;
import com.iaminca.openai.service.bo.UserBO;
import com.iaminca.openai.service.bo.UserBalanceBO;
import com.iaminca.openai.service.bo.UserLoginBO;
import com.iaminca.openai.service.bo.UserRegisterBO;
import com.iaminca.openai.utils.CodeUtil;
import com.iaminca.openai.utils.RedisKeyUtil;
import com.iaminca.openai.utils.sms.SendSmsBO;
import com.iaminca.openai.utils.sms.SmsBusinessEnum;
import com.iaminca.openai.utils.sms.SmsSendUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
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
    @Value("${spring.profiles.active}")
    private String env;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate<String,Integer> redisTemplateIntegerValue;
    @Resource
    private UserService userService;
    @Resource
    private UserBalanceHandler userBalanceHandler;

    public void sendCode(UserBO userBO){
        //TODO Send Verification Code
        String randomNumberString = "111111";
        if(!StringUtils.isEmpty(env) && !"dev".equals(env)){
             randomNumberString = CodeUtil.getRandomNumberString();
        }
        //Find user,
        UserBO userByPhone = this.findUserByPhone(userBO.getUserPhone());
        if(!ObjectUtils.isEmpty(userByPhone)){
            throw new BusinessException(ErrorCode.USER_EXIST_ERROR);
        }
        SendSmsBO sendSmsBO=new SendSmsBO();
        sendSmsBO.setCode(randomNumberString);
        SmsSendUtil smsSendUtil=new SmsSendUtil();
        smsSendUtil.sendSms(userBO.getUserPhone(), SmsBusinessEnum.PHONE_REGISTER,sendSmsBO);
        // Save in Redis as Cache.

        stringRedisTemplate.opsForValue().set(RedisKeyUtil.registerCodeKey(userBO.getUserPhone()),randomNumberString,10, TimeUnit.MINUTES);
    }

    public Long findUserBalance(Long userId){
        String userBalance = stringRedisTemplate.opsForValue().get(RedisKeyUtil.getUserBalance(userId));
        if(StringUtils.isEmpty(userBalance)){
            return 0L;
        }
        return Long.valueOf(userBalance);
    }

    public UserBO findUser(UserQuery query){
        List<UserBO> list = userService.findList(query);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }


    public UserBO findUserByPhone(String phone){
        UserQuery query = new UserQuery();
        query.setUserPhone(phone);
        List<UserBO> list = userService.findList(query);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }

    @Transactional(rollbackFor = Exception.class)
    public String chekVerificationCode(UserRegisterBO userRegisterBO){
        UserQuery query = new UserQuery();
        query.setUserPhone(userRegisterBO.getUserPhone());
        List<UserBO> list = userService.findList(query);
        if(CollectionUtils.isEmpty(list)){
            list = new ArrayList<>();
            UserBO userBO = new UserBO();
            userBO.setUserPhone(userRegisterBO.getUserPhone());
            userBO.setPassword(userRegisterBO.getPassword());
            userBO.setUserName(userBO.getUserPhone());
            userBO.setUserType(UserTypeEnum.USER.getCode());
            userBO.setUserChatLimitation(CHAT_LIMITATION);
            userBO.setUserLengthLimitation(LENGTH_LIMITATION);
            log.info("Add UserBO: {}", Constants.GSON.toJson(userBO));
            UserBO userNewBO = userService.add(userBO);
            userBalanceHandler.addNewUserBalance(userNewBO.getId());
            list.add(userNewBO);
        }
        UserBO userBO = list.get(0);
        //TODO Check Verification Code
        String verificationCodeCache = stringRedisTemplate.opsForValue().get(RedisKeyUtil.registerCodeKey(userBO.getUserPhone()));
        if(StringUtils.isEmpty(verificationCodeCache) || !verificationCodeCache.equals(userRegisterBO.getVerificationCode())){
            //Checked Unsuccessfully
            log.info("Verification Code : {} -- {}",userRegisterBO.getVerificationCode(),verificationCodeCache);
            throw new BusinessException(ErrorCode.USER_VERIFICATION_CODE_ERROR);
        }
        stringRedisTemplate.delete(RedisKeyUtil.registerCodeKey(userBO.getUserPhone()));

//        stringRedisTemplate.delete(RedisKeyUtil.registerCodeKey(userBO.getUserPhone()));
        //Save the token and information to Cache
        String token = CodeUtil.getUpperUUID();
//        stringRedisTemplate.opsForValue().set(RedisKeyUtil.userInfoKey(token), Constants.GSON.toJson(userBO));
//        stringRedisTemplate.opsForValue().set(RedisKeyUtil.phoneAndToken(userRegisterBO.getUserPhone()), token);
        return token;
    }

    @Transactional(rollbackFor = Exception.class)
    public String passwordLogin(UserLoginBO userLoginBO){
        if(ObjectUtils.isEmpty(userLoginBO) || StringUtils.isEmpty(userLoginBO.getPassword())
                || StringUtils.isEmpty(userLoginBO.getUserPhone())){
            throw new BusinessException(ErrorCode.USER_PHONE_PASSWORD_ERROR);
        }
        UserQuery query = new UserQuery();
        query.setUserPhone(userLoginBO.getUserPhone());
        List<UserBO> list = userService.findList(query);
        if(CollectionUtils.isEmpty(list)){
           throw new BusinessException(ErrorCode.USER_PHONE_ERROR);
        }
        UserBO userBO = list.get(0);
        //TODO Check Verification Code
        if( !userLoginBO.getPassword().equals(userBO.getPassword())){
            //Checked Unsuccessfully
            throw new BusinessException(ErrorCode.USER_PHONE_PASSWORD_ERROR);
        }
        //Delete the older message
        String oldToken = stringRedisTemplate.opsForValue().get(RedisKeyUtil.phoneAndToken(userLoginBO.getUserPhone()));
        stringRedisTemplate.delete(RedisKeyUtil.phoneAndToken(userLoginBO.getUserPhone()));
        if(!StringUtils.isEmpty(oldToken)){
            stringRedisTemplate.delete(RedisKeyUtil.userInfoKey(oldToken));
        }
        UserBalanceBO userBalance = userBalanceHandler.findUserBalance(userBO.getId());
        if(!ObjectUtils.isEmpty(userBalance)){
            redisTemplateIntegerValue.opsForValue().set(RedisKeyUtil.getUserBalance(userBO.getId()),userBalance.getUserBalance());
        }
        //Save the token and information to Cache
        String token = CodeUtil.getUpperUUID();
        stringRedisTemplate.opsForValue().set(RedisKeyUtil.userInfoKey(token), Constants.GSON.toJson(userBO));
        stringRedisTemplate.opsForValue().set(RedisKeyUtil.phoneAndToken(userLoginBO.getUserPhone()), token);
        return token;
    }

}