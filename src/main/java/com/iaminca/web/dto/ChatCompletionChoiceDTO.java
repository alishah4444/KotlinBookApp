package com.iaminca.web.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ChatCompletionChoiceDTO implements Serializable {
    Integer index;
    @JsonAlias({"delta"})
    ChatMessageDTO message;
    @JsonProperty("finish_reason")
    String finishReason;
}
