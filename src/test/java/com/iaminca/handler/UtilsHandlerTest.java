package com.iaminca.handler;

import com.iaminca.OpenaiApplicationTests;
import com.iaminca.service.bo.UserBO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

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
    private RedisTemplate redisTemplate;

    @Test
    public void addUser() {
        redisTemplate.opsForValue().set("a","xw");
        System.out.println("DONE.");
    }

}
