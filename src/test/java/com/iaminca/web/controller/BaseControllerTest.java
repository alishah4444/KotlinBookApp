package com.iaminca.web.controller;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.reactive.server.WebTestClientBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;
import java.util.Collections;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseControllerTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void testListModels() {
        webClient.get().uri("/v1/models").exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.object").isEqualTo("list")
                .jsonPath("$.data").isArray();
    }

    @Test
    public void testCreateChatCompletion() {
        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(Collections.singletonList(
                        new ChatMessage("system", "You are a dog and will speak as such.")
                ))
                .n(1)
                .maxTokens(50)
                .logitBias(Collections.emptyMap())
                .build();
        webClient.post().uri("/v1/chat/completions").bodyValue(request).exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.usage.total_tokens").value(Matchers.greaterThan(0));
    }

    @TestConfiguration
    static class Config {
        @Bean
        public WebTestClientBuilderCustomizer customizer() {
            return builder -> builder.responseTimeout(Duration.ofMinutes(1));
        }
    }
}
