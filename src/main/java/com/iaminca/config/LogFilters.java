package com.iaminca.config;

import com.iaminca.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

@Component
@Slf4j
public class LogFilters {

    @Bean
    public WebFilter someFilter() {
        return (ServerWebExchange exchange, WebFilterChain chain) -> {
            log.info("WebFilter Log Start:");
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("WebFilter Log request:{}", Constants.GSON.toJson(request));
            log.info("WebFilter Log response:{}", Constants.GSON.toJson(response));
            return chain.filter(exchange);
        };
    }
}
