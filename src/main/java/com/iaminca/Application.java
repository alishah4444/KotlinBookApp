package com.iaminca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@tk.mybatis.spring.annotation.MapperScan(basePackages = {"com.iaminca.dal.dao"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    public RestTemplate getRestTemplate() {
//        return new RestTemplate();
//    }
}
