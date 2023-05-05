package com.iaminca.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Getter
@Setter
@ConfigurationProperties(prefix = "openai")
public class OpenAiProperties {
    private String token;
    private boolean followRedirects = true;
    private Duration callTimeout = Duration.ZERO;
    private Duration connectTimeout = Duration.ofSeconds(10);
    private Duration readTimeout = Duration.ofSeconds(10);
    private Duration writeTimeout = Duration.ofSeconds(10);
    private Duration pingInterval = Duration.ZERO;
    private final Pool pool = new Pool();
    private String baseUrl = "https://api.openai.com/";

    @Getter
    @Setter
    public static class Pool {
        private int maxIdleConnections = 5;
        private Duration keepAlive = Duration.ofSeconds(1);
    }
}
