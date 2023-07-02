package com.iaminca.openai.web.controller;

import com.iaminca.openai.web.dto.UserRegisterDTO;
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
                .expectBody();
//                .jsonPath("$.usage.total_tokens").value(Matchers.greaterThan(0));
    }


    @Test
    public void testApplyKey() {
        webClient.post().uri("/user/applyKey").bodyValue("{}").exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .expectBody();
//                .jsonPath("$.usage.total_tokens").value(Matchers.greaterThan(0));
    }


    @Test
    public void testSendUser() {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setUserPhone("15869007707");
        webClient.post().uri("/user/sendCode").bodyValue(userRegisterDTO).exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .expectBody();
//                .jsonPath("$.usage.total_tokens").value(Matchers.greaterThan(0));
    }



}