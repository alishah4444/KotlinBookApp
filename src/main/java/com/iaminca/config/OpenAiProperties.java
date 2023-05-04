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
    private Duration timeout = Duration.ofSeconds(10);
}
