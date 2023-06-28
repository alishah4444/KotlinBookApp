package com.iaminca.openai.web.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("test/session")
public class WebSessionTestController {

    private static final String SESSION_KEY = "test-user";

    @GetMapping("user")
    public Map<String, String> testGet(WebSession session) {
        return Collections.singletonMap("user", session.getAttribute(SESSION_KEY));
    }

    @PostMapping("login")
    public Mono<Map<String, String>> testSet(WebSession session) {
        String value = Long.toHexString(ThreadLocalRandom.current().nextLong());
        session.getAttributes().put(SESSION_KEY, value);
        return session.save().thenReturn(Collections.singletonMap("msg", "set to " + value));
    }

    @DeleteMapping("user")
    public Mono<Map<String, String>> logout(WebSession session) {
        session.getAttributes().remove(SESSION_KEY);
        return session.save().thenReturn(Collections.singletonMap("msg", "user deleted"));
    }

    @DeleteMapping
    public Mono<Map<String, String>> invalidate(WebSession session) {
        return session.invalidate().thenReturn(Collections.singletonMap("msg", "session invalidated"));
    }

    @GetMapping("user_required")
    public Map<String, String> testRequiredGet(@SessionAttribute(SESSION_KEY) String user) {
        return Collections.singletonMap("user", user);
    }

    @GetMapping("user_optional")
    public Map<String, String> testOptionalGet(@SessionAttribute(name = SESSION_KEY, required = false) String user) {
        return Collections.singletonMap("user", user);
    }
}
