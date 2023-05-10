package com.iaminca.handler;

import com.iaminca.OpenaiApplicationTests;
import com.iaminca.service.bo.UserBO;
import com.iaminca.utils.IDUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * @program: auth
 * @description: AdminHandleTest
 * @create: 2023-05-13 00:58
 **/
public class UtilsHandlerTest extends OpenaiApplicationTests {
    @Resource
    private UserHandler userHandler;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void addUser() {
//        stringRedisTemplate.opsForValue().set("a","xw");
        String str = stringRedisTemplate.opsForValue().get("user:info:CAFB62D795B1468DAFA2A2C53086BF4B");
        System.out.println(str);
        System.out.println("DONE.");
    }


    @Test
    public void idUtils() {
        System.out.println(IDUtil.getRecordCycleID());
        System.out.println("DONE.");
    }

}
