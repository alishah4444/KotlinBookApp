package com.iaminca.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.CompletionChunk;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.completion.chat.ChatCompletionChunk;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import lombok.Lombok;
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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseControllerTest {

    @Autowired
    private WebTestClient webClient;
    @Autowired
    private ObjectMapper mapper;

    private <T> Function<String, T> uncheckedReadValue(Class<T> clazz) {
        return data -> {
            try {
                return mapper.readValue(data, clazz);
            } catch (JsonProcessingException ex) {
                throw Lombok.sneakyThrow(ex);
            }
        };
    }

    @Test
    public void testListModels() {
        webClient.get().uri("/v1/models").exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.object").isEqualTo("list")
                .jsonPath("$.data").isArray();
    }

    private CompletionRequest.CompletionRequestBuilder completionRequestBuilder() {
        return CompletionRequest.builder()
                .model("ada")
                .prompt("Somebody once told me the world is gonna roll me")
                .echo(true)
                .user("testing")
                .n(3);
    }

    @Test
    public void testCreateCompletion() {
        CompletionRequest request = completionRequestBuilder().build();
        assertThat(webClient.post().uri("/v1/completions").bodyValue(request).exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .returnResult(CompletionResult.class)
                .getResponseBody().single().blockOptional().map(CompletionResult::getId)).isNotEmpty();
    }

    private <Tp, Id> void assertSseIdentity(
            WebTestClient.RequestHeadersSpec<?> spec, Class<Tp> chunkClass, Function<Tp, Id> identity) {
        spec.exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(MediaType.TEXT_EVENT_STREAM)
                .returnResult(String.class)
                .consumeWith(it -> {
                    AtomicBoolean bool = new AtomicBoolean();
                    assertThat(it.getResponseBody()
                            .takeWhile(x -> {
                                if ("[DONE]".equals(x)) {
                                    bool.set(true);
                                    return false;
                                }
                                return true;
                            })
                            .map(uncheckedReadValue(chunkClass))
                            .map(identity).distinct().toStream()).hasSize(1);
                    assertThat(bool).isTrue();
                });
    }

    @Test
    public void testStreamCompletion() {
        CompletionRequest request = completionRequestBuilder().stream(true).build();
        assertSseIdentity(
                webClient.post().uri("/v1/completions").bodyValue(request),
                CompletionChunk.class, CompletionChunk::getId);
    }

    private ChatCompletionRequest.ChatCompletionRequestBuilder chatCompletionRequestBuilder() {
        return ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(Collections.singletonList(
                        new ChatMessage("user", "You are a dog and will speak as such.")
                ))
                .n(1)
                .maxTokens(50)
                .logitBias(Collections.emptyMap());
    }

    @Test
    public void testCreateChatCompletion() {
        ChatCompletionRequest request = chatCompletionRequestBuilder().build();
        webClient.post().uri("/v1/chat/completions").bodyValue(request).exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.usage.total_tokens").value(Matchers.greaterThan(0));
    }

    @Test
    public void testStreamChatCompletion() {
        ChatCompletionRequest request = chatCompletionRequestBuilder().stream(true).build();
        assertSseIdentity(
                webClient.post().uri("/v1/chat/completions").bodyValue(request),
                ChatCompletionChunk.class, ChatCompletionChunk::getId);
    }

    @TestConfiguration
    static class Config {
        @Bean
        public WebTestClientBuilderCustomizer customizer() {
            return builder -> builder.responseTimeout(Duration.ofMinutes(1));
        }
    }
}
