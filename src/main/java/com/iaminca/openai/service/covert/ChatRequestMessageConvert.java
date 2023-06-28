package com.iaminca.openai.service.covert;

import com.google.common.collect.Lists;
import com.iaminca.openai.dal.dataobject.ChatRequestMessageDO;
import com.iaminca.openai.service.bo.ChatRequestMessageBO;
import com.theokanning.openai.completion.chat.ChatMessage;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * 
 *
 * @author xw
 * @email xw
 * @date 2023-05-04 09:54:43
 */
public class ChatRequestMessageConvert {

	public static ChatMessage toGpt(ChatRequestMessageBO chatRequestMessageBO) {
		if (chatRequestMessageBO == null) {
			return null;
		}
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setRole(chatRequestMessageBO.getRole());
		chatMessage.setContent(chatRequestMessageBO.getContent());
		return chatMessage;
	}
	public static List<ChatMessage> toGptList(List<ChatRequestMessageBO> dtoList) {
		if (CollectionUtils.isEmpty(dtoList)) {
			return Collections.emptyList();
		}

		List<ChatMessage> doList = Lists.newArrayList();
		for (ChatRequestMessageBO chatRequestMessageBO : dtoList) {
			if (chatRequestMessageBO != null) {
				doList.add(toGpt(chatRequestMessageBO));
			}
		}
		return doList;
	}

	public static ChatRequestMessageBO toBO(ChatRequestMessageDO chatRequestMessageDO) {
		if (chatRequestMessageDO == null) {
			return null;
		}
		ChatRequestMessageBO chatRequestMessageBO = new ChatRequestMessageBO();
		chatRequestMessageBO.setId(chatRequestMessageDO.getId());
		chatRequestMessageBO.setChatRequestId(chatRequestMessageDO.getChatRequestId());
		chatRequestMessageBO.setRole(chatRequestMessageDO.getRole());
		chatRequestMessageBO.setContent(chatRequestMessageDO.getContent());
		chatRequestMessageBO.setDelFlag(chatRequestMessageDO.getDelFlag());
		chatRequestMessageBO.setCreateTime(chatRequestMessageDO.getCreateTime());
		chatRequestMessageBO.setUpdateTime(chatRequestMessageDO.getUpdateTime());
		return chatRequestMessageBO;
	}

	public static ChatRequestMessageDO toDO(ChatRequestMessageBO chatRequestMessageBO) {
		if (chatRequestMessageBO == null) {
			return null;
		}
		ChatRequestMessageDO chatRequestMessageDO = new ChatRequestMessageDO();
		chatRequestMessageDO.setId(chatRequestMessageBO.getId());
		chatRequestMessageDO.setChatRequestId(chatRequestMessageBO.getChatRequestId());
		chatRequestMessageDO.setRole(chatRequestMessageBO.getRole());
		chatRequestMessageDO.setContent(chatRequestMessageBO.getContent());
		chatRequestMessageDO.setDelFlag(chatRequestMessageBO.getDelFlag());
		chatRequestMessageDO.setCreateTime(chatRequestMessageBO.getCreateTime());
		chatRequestMessageDO.setUpdateTime(chatRequestMessageBO.getUpdateTime());
		return chatRequestMessageDO;
	}

	public static List<ChatRequestMessageBO> toBOList(List<ChatRequestMessageDO> doList) {
        if (CollectionUtils.isEmpty(doList)) {
			return Collections.emptyList();
		}
		List<ChatRequestMessageBO> dtoList = Lists.newArrayList();
		for (ChatRequestMessageDO chatRequestMessageDO : doList) {
			if (chatRequestMessageDO != null) {
				dtoList.add(toBO(chatRequestMessageDO));
			}
		}
		return dtoList;
	}

	public static List<ChatRequestMessageDO> toDOList(List<ChatRequestMessageBO> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return Collections.emptyList();
		}

		List<ChatRequestMessageDO> doList = Lists.newArrayList();
		for (ChatRequestMessageBO chatRequestMessageBO : dtoList) {
			if (chatRequestMessageBO != null) {
				doList.add(toDO(chatRequestMessageBO));
			}
		}
		return doList;
	}



}
