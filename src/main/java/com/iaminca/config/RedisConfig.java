package com.iaminca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

//    @Bean
//    LettuceConnectionFactory redisConnectionFactory() {
//        return new LettuceConnectionFactory();
//    }
//
//    @Bean
//    RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//
//        RedisTemplate<String, String> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//        return template;
//    }


    @Bean("redisTemplate")
    public RedisTemplate redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(factory);
        //设置序列化Key的实例化对象
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置序列化Value的实例化对象
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }
}
