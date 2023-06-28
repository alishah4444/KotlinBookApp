package com.iaminca.openai.config;

import com.iaminca.openai.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import java.util.List;

@Component
@Slf4j
public class LogFilters {

    @Bean
    public WebFilter someFilter() {
        return (ServerWebExchange exchange, WebFilterChain chain) -> {
            log.info("WebFilter Log Start:");
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            HttpHeaders headers = request.getHeaders();
            List<String> token = headers.get("token");
            List<String> authorization = headers.get("Authorization");

            log.info("===== URL : {}",request.getURI());
            log.info("===== TOKEN : {}", Constants.GSON.toJson(token));
            log.info("===== Authorization : {}",Constants.GSON.toJson(authorization));

//            log.info("WebFilter Log request:{}", Constants.GSON.toJson(request));
//            log.info("WebFilter Log response:{}", Constants.GSON.toJson(response));
            return chain.filter(exchange);
        };
    }
}
