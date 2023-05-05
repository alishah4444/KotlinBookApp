package com.iaminca.web.controller;

import com.iaminca.web.dto.UserRegisterDTO;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @Autowired
    private WebTestClient webClient;

    @Test
    public void testAddUser() {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setUserPhone("5199989118");
        webClient.post().uri("/user/register").bodyValue(userRegisterDTO).exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.usage.total_tokens").value(Matchers.greaterThan(0));
    }


    @Test
    public void testApplyKey() {
        webClient.post().uri("/user/applyKey").bodyValue("{}").exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .expectBody();
//                .jsonPath("$.usage.total_tokens").value(Matchers.greaterThan(0));
    }

}
