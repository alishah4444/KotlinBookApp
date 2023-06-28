package com.iaminca.openai.web.dto;

import com.theokanning.openai.Usage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ChatCompletionResultDTO implements Serializable {
    String id;
    String object;
    long created;
    String model;
    List<ChatCompletionChoiceDTO> choices;
    Usage usage;
}
