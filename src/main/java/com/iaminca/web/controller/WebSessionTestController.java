package com.iaminca.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("test/session")
public class WebSessionTestController {

    @GetMapping("current")
    public Object testGet(WebSession session) {
        return Collections.singletonMap("user", session.getAttribute("user"));
    }

    @PostMapping("login")
    public Mono<Map<String, String>> testSet(WebSession session) {
        session.getAttributes().put("user", "abcdef");
        return session.save().thenReturn(Collections.singletonMap("msg", "ok"));
    }
}
