package com.iaminca.web.convert;

import com.google.common.collect.Lists;
import com.iaminca.service.bo.ChatRequestBO;
import com.iaminca.service.covert.ChatRequestMessageConvert;
import com.iaminca.web.dto.ChatCompletionRequestDTO;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * 
 *
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:23
 */
public class ChatCompletionRequestDTOConvert {

	public static ChatCompletionRequest toGptBO(ChatRequestBO chatRequestBO) {
		if (chatRequestBO == null) {
			return null;
		}
		ChatCompletionRequest chatCompletionRequest = new ChatCompletionRequest();
		chatCompletionRequest.setMessages(ChatRequestMessageConvert.toGptList(chatRequestBO.getMessages()));
		chatCompletionRequest.setModel(chatRequestBO.getModel());
		chatCompletionRequest.setTemperature(chatRequestBO.getTemperature());
		chatCompletionRequest.setTopP(chatRequestBO.getTopP());
		chatCompletionRequest.setN(chatRequestBO.getN());
		chatCompletionRequest.setStream(chatRequestBO.getStream());
		chatCompletionRequest.setStop(chatRequestBO.getStop());
		chatCompletionRequest.setMaxTokens(chatRequestBO.getMaxTokens());
		chatCompletionRequest.setPresencePenalty(chatRequestBO.getPresencePenalty());
		chatCompletionRequest.setFrequencyPenalty(chatRequestBO.getFrequencyPenalty());
		chatCompletionRequest.setLogitBias(chatRequestBO.getLogitBias());
		chatCompletionRequest.setUser(chatRequestBO.getUser());

		return chatCompletionRequest;
	}
	public static ChatRequestBO toBO(ChatCompletionRequestDTO chatCompletionRequestDTO) {
		if (chatCompletionRequestDTO == null) {
			return null;
		}
		ChatRequestBO chatRequestBO = new ChatRequestBO();

		chatRequestBO.setModel(chatCompletionRequestDTO.getModel());
		chatRequestBO.setTemperature(chatCompletionRequestDTO.getTemperature());
		chatRequestBO.setTopP(chatCompletionRequestDTO.getTopP());
		chatRequestBO.setN(chatCompletionRequestDTO.getN());
		chatRequestBO.setStream(chatCompletionRequestDTO.getStream());
		chatRequestBO.setStop(chatCompletionRequestDTO.getStop());
		chatRequestBO.setMaxTokens(chatCompletionRequestDTO.getMaxTokens());
		chatRequestBO.setPresencePenalty(chatCompletionRequestDTO.getPresencePenalty());
		chatRequestBO.setFrequencyPenalty(chatCompletionRequestDTO.getFrequencyPenalty());
		chatRequestBO.setLogitBias(chatCompletionRequestDTO.getLogitBias());
		chatRequestBO.setUser(chatCompletionRequestDTO.getUser());
		return chatRequestBO;
	}

	public static ChatCompletionRequestDTO toDTO(ChatRequestBO chatRequestBO) {
		if (chatRequestBO == null) {
			return null;
		}
		ChatCompletionRequestDTO chatCompletionRequestDTO = new ChatCompletionRequestDTO();
		chatCompletionRequestDTO.setModel(chatRequestBO.getModel());
		chatCompletionRequestDTO.setTemperature(chatRequestBO.getTemperature());
		chatCompletionRequestDTO.setTopP(chatRequestBO.getTopP());
		chatCompletionRequestDTO.setN(chatRequestBO.getN());
		chatCompletionRequestDTO.setStream(chatRequestBO.getStream());
		chatCompletionRequestDTO.setStop(chatRequestBO.getStop());
		chatCompletionRequestDTO.setMaxTokens(chatRequestBO.getMaxTokens());
		chatCompletionRequestDTO.setPresencePenalty(chatRequestBO.getPresencePenalty());
		chatCompletionRequestDTO.setFrequencyPenalty(chatRequestBO.getFrequencyPenalty());
		chatCompletionRequestDTO.setLogitBias(chatRequestBO.getLogitBias());
		chatCompletionRequestDTO.setUser(chatRequestBO.getUser());
		return chatCompletionRequestDTO;
	}

	public static List<ChatRequestBO> toBOList(List<ChatCompletionRequestDTO> doList) {
        if (CollectionUtils.isEmpty(doList)) {
			return Collections.emptyList();
		}
		List<ChatRequestBO> dtoList = Lists.newArrayList();
		for (ChatCompletionRequestDTO chatCompletionRequestDTO : doList) {
			if (chatCompletionRequestDTO != null) {
				dtoList.add(toBO(chatCompletionRequestDTO));
			}
		}
		return dtoList;
	}

	public static List<ChatCompletionRequestDTO> toDTOList(List<ChatRequestBO> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return Collections.emptyList();
		}

		List<ChatCompletionRequestDTO> doList = Lists.newArrayList();
		for (ChatRequestBO chatRequestBO : dtoList) {
			if (chatRequestBO != null) {
				doList.add(toDTO(chatRequestBO));
			}
		}
		return doList;
	}

}
