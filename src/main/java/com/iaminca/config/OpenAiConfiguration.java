package com.iaminca.config;

import com.theokanning.openai.OpenAiApi;
import com.theokanning.openai.service.OpenAiService;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Configuration
public class OpenAiConfiguration {

    @Bean
    public OpenAiApi openAiApi(OpenAiClient openAiClient) {
        return OpenAiService.defaultRetrofit(openAiClient.get(), OpenAiService.defaultObjectMapper()).create(OpenAiApi.class);
    }

    @Bean(destroyMethod = "shutdownExecutor")
    public OpenAiService openAiService(OpenAiApi openAiApi, OpenAiClient openAiClient) {
        return new OpenAiService(openAiApi, openAiClient.get().dispatcher().executorService());
    }

    @Component
    static class OpenAiClient {
        private final OkHttpClient value;

        OpenAiClient(@Value("${openai.token}") String token) {
            this.value = OpenAiService.defaultClient(token, Duration.ofSeconds(10));
        }

        public OkHttpClient get() {
            return value;
        }
    }
}
