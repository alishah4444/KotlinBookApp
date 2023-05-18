package com.iaminca.web.convert;

import com.google.common.collect.Lists;
import com.iaminca.service.bo.UserKeyBO;
import com.iaminca.web.dto.CompletionRequestDTO;
import com.iaminca.web.dto.UserKeyDTO;
import com.theokanning.openai.completion.CompletionRequest;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * 
 *
 * @author xw
 * @email xw
 * @date 2023-05-04 17:34:01
 */
public class CompletionRequestConvertDTO {

	public static CompletionRequest toRequestBO(CompletionRequestDTO completionRequestDTO) {
		if (completionRequestDTO == null) {
			return null;
		}
		CompletionRequest completionRequest = new CompletionRequest();
		completionRequest.setModel(completionRequestDTO.getModel());
		completionRequest.setPrompt(completionRequestDTO.getPrompt());
		completionRequest.setSuffix(completionRequestDTO.getSuffix());
		completionRequest.setMaxTokens(completionRequestDTO.getMaxTokens());
		completionRequest.setTemperature(completionRequestDTO.getTemperature());
		completionRequest.setTopP(completionRequestDTO.getTopP());
		completionRequest.setN(completionRequestDTO.getN());
		completionRequest.setStream(completionRequestDTO.getStream());
		completionRequest.setLogprobs(completionRequestDTO.getLogprobs());
		completionRequest.setEcho(completionRequestDTO.getEcho());
		completionRequest.setStop(completionRequestDTO.getStop());
		completionRequest.setPresencePenalty(completionRequestDTO.getPresencePenalty());
		completionRequest.setFrequencyPenalty(completionRequestDTO.getFrequencyPenalty());
		completionRequest.setBestOf(completionRequestDTO.getBestOf());
		completionRequest.setLogitBias(completionRequestDTO.getLogitBias());
		completionRequest.setUser(completionRequestDTO.getUser());
		return completionRequest;
	}

}
