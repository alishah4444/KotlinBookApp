package com.iaminca.openai.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class ChatCompletionRequestDTO implements Serializable {

    String model;
    List<ChatMessageDTO> messages;
    Double temperature;
    @JsonProperty("top_p")
    Double topP;
    Integer n;
    Boolean stream;
    List<String> stop;
    @JsonProperty("max_tokens")
    Integer maxTokens;
    @JsonProperty("presence_penalty")
    Double presencePenalty;
    @JsonProperty("frequency_penalty")
    Double frequencyPenalty;
    @JsonProperty("logit_bias")
    Map<String, Integer> logitBias;
    String user;
    String gptTestKey;
}
