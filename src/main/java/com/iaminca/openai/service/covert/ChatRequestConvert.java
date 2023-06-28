package com.iaminca.openai.service.covert;

import com.google.common.collect.Lists;
import com.iaminca.openai.common.Constants;
import com.iaminca.openai.dal.dataobject.ChatRequestDO;
import com.iaminca.openai.service.bo.ChatRequestBO;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:23
 */
public class ChatRequestConvert {

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
	public static ChatRequestBO toBO(ChatRequestDO chatRequestDO) {
		if (chatRequestDO == null) {
			return null;
		}
		ChatRequestBO chatRequestBO = new ChatRequestBO();
		chatRequestBO.setId(chatRequestDO.getId());
		chatRequestBO.setUserId(chatRequestDO.getUserId());
		chatRequestBO.setKeyId(chatRequestDO.getKeyId());
		chatRequestBO.setRecordId(chatRequestDO.getRecordId());
		chatRequestBO.setGptKey(chatRequestDO.getGptKey());
		chatRequestBO.setModel(chatRequestDO.getModel());
		chatRequestBO.setTemperature(chatRequestDO.getTemperature());
		chatRequestBO.setTopP(chatRequestDO.getTopP());
		chatRequestBO.setN(chatRequestDO.getN());
		chatRequestBO.setStream(chatRequestDO.getStream()==0?false:true);
		chatRequestBO.setStop(Constants.GSON.fromJson(chatRequestDO.getStop(),List.class));
		chatRequestBO.setMaxTokens(chatRequestDO.getMaxTokens());
		chatRequestBO.setPresencePenalty(chatRequestDO.getPresencePenalty());
		chatRequestBO.setFrequencyPenalty(chatRequestDO.getFrequencyPenalty());
		chatRequestBO.setLogitBias(Constants.GSON.fromJson(chatRequestDO.getLogitBias(), Map.class));
		chatRequestBO.setUser(chatRequestDO.getUser());
		chatRequestBO.setDelFlag(chatRequestDO.getDelFlag());
		chatRequestBO.setCreateTime(chatRequestDO.getCreateTime());
		chatRequestBO.setUpdateTime(chatRequestDO.getUpdateTime());
		return chatRequestBO;
	}

	public static ChatRequestDO toDO(ChatRequestBO chatRequestBO) {
		if (chatRequestBO == null) {
			return null;
		}
		ChatRequestDO chatRequestDO = new ChatRequestDO();
		chatRequestDO.setId(chatRequestBO.getId());
		chatRequestDO.setUserId(chatRequestBO.getUserId());
		chatRequestDO.setKeyId(chatRequestBO.getKeyId());
		chatRequestDO.setRecordId(chatRequestBO.getRecordId());
		chatRequestDO.setGptKey(chatRequestBO.getGptKey());
		chatRequestDO.setModel(chatRequestBO.getModel());
		chatRequestDO.setTemperature(chatRequestBO.getTemperature());
		chatRequestDO.setTopP(chatRequestBO.getTopP());
		chatRequestDO.setN(chatRequestBO.getN());
		chatRequestDO.setStream(chatRequestBO.getStream()?1:0);
		chatRequestDO.setStop(Constants.GSON.toJson(chatRequestBO.getStop()));
		chatRequestDO.setMaxTokens(chatRequestBO.getMaxTokens());
		chatRequestDO.setPresencePenalty(chatRequestBO.getPresencePenalty());
		chatRequestDO.setFrequencyPenalty(chatRequestBO.getFrequencyPenalty());
		chatRequestDO.setLogitBias(Constants.GSON.toJson(chatRequestBO.getLogitBias()));
		chatRequestDO.setUser(chatRequestBO.getUser());
		chatRequestDO.setDelFlag(chatRequestBO.getDelFlag());
		chatRequestDO.setCreateTime(chatRequestBO.getCreateTime());
		chatRequestDO.setUpdateTime(chatRequestBO.getUpdateTime());
		return chatRequestDO;
	}

	public static List<ChatRequestBO> toBOList(List<ChatRequestDO> doList) {
        if (CollectionUtils.isEmpty(doList)) {
			return Collections.emptyList();
		}
		List<ChatRequestBO> dtoList = Lists.newArrayList();
		for (ChatRequestDO chatRequestDO : doList) {
			if (chatRequestDO != null) {
				dtoList.add(toBO(chatRequestDO));
			}
		}
		return dtoList;
	}

	public static List<ChatRequestDO> toDOList(List<ChatRequestBO> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return Collections.emptyList();
		}

		List<ChatRequestDO> doList = Lists.newArrayList();
		for (ChatRequestBO chatRequestBO : dtoList) {
			if (chatRequestBO != null) {
				doList.add(toDO(chatRequestBO));
			}
		}
		return doList;
	}

}
