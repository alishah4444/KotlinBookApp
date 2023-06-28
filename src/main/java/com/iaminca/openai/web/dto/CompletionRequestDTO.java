package com.iaminca.openai.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class CompletionRequestDTO implements Serializable {

    String model;
    String prompt;
    String suffix;
    @JsonProperty("max_tokens")
    Integer maxTokens;
    Double temperature;
    @JsonProperty("top_p")
    Double topP;
    Integer n;
    Boolean stream;
    Integer logprobs;
    Boolean echo;
    List<String> stop;
    @JsonProperty("presence_penalty")
    Double presencePenalty;
    @JsonProperty("frequency_penalty")
    Double frequencyPenalty;
    @JsonProperty("best_of")
    Integer bestOf;
    @JsonProperty("logit_bias")
    Map<String, Integer> logitBias;
    String user;
}
