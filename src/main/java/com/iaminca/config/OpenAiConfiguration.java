package com.iaminca.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.OpenAiApi;
import com.theokanning.openai.service.OpenAiService;
import okhttp3.OkHttpClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@EnableConfigurationProperties(OpenAiProperties.class)
public class OpenAiConfiguration {

    @Bean
    public ObjectMapper jacksonObjectMapper() {
        return OpenAiService.defaultObjectMapper();
    }

    @Bean
    public OpenAiApi openAiApi(OpenAiClient openAiClient, ObjectMapper mapper) {
        return OpenAiService.defaultRetrofit(openAiClient.get(), mapper).create(OpenAiApi.class);
    }

    @Bean(destroyMethod = "shutdownExecutor")
    public OpenAiService openAiService(OpenAiApi openAiApi, OpenAiClient openAiClient) {
        return new OpenAiService(openAiApi, openAiClient.get().dispatcher().executorService());
    }

    @Component
    static class OpenAiClient {
        private final OkHttpClient value;

        OpenAiClient(OpenAiProperties properties) {
            this.value = OpenAiService.defaultClient(properties.getToken(), properties.getTimeout());
        }

        public OkHttpClient get() {
            return value;
        }
    }
}
