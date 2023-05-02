package com.example;

import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiConfiguration {

    private final String token;

    public OpenAiConfiguration(@Value("${openai.token}") String token) {
        this.token = token;
    }

    @Bean(destroyMethod = "shutdownExecutor")
    public OpenAiService openAiService() {
        return new OpenAiService(token);
    }
}
