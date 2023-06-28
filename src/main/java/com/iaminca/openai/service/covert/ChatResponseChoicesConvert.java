package com.iaminca.openai.service.covert;


import com.google.common.collect.Lists;
import com.iaminca.openai.dal.dataobject.ChatResponseChoicesDO;
import com.iaminca.openai.service.bo.ChatResponseChoicesBO;
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
public class ChatResponseChoicesConvert {

	public static ChatResponseChoicesBO toBO(ChatResponseChoicesDO chatResponseChoicesDO) {
		if (chatResponseChoicesDO == null) {
			return null;
		}
		ChatResponseChoicesBO chatResponseChoicesBO = new ChatResponseChoicesBO();
		chatResponseChoicesBO.setId(chatResponseChoicesDO.getId());
		chatResponseChoicesBO.setResponseId(chatResponseChoicesDO.getResponseId());
		chatResponseChoicesBO.setChatIndex(chatResponseChoicesDO.getChatIndex());
		chatResponseChoicesBO.setMessageRole(chatResponseChoicesDO.getMessageRole());
		chatResponseChoicesBO.setMessageContent(chatResponseChoicesDO.getMessageContent());
		chatResponseChoicesBO.setMessageFinishReason(chatResponseChoicesDO.getMessageFinishReason());
		chatResponseChoicesBO.setCreateTime(chatResponseChoicesDO.getCreateTime());
		chatResponseChoicesBO.setUpdateTime(chatResponseChoicesDO.getUpdateTime());
		return chatResponseChoicesBO;
	}

	public static ChatResponseChoicesDO toDO(ChatResponseChoicesBO chatResponseChoicesBO) {
		if (chatResponseChoicesBO == null) {
			return null;
		}
		ChatResponseChoicesDO chatResponseChoicesDO = new ChatResponseChoicesDO();
		chatResponseChoicesDO.setId(chatResponseChoicesBO.getId());
		chatResponseChoicesDO.setResponseId(chatResponseChoicesBO.getResponseId());
		chatResponseChoicesDO.setChatIndex(chatResponseChoicesBO.getChatIndex());
		chatResponseChoicesDO.setMessageRole(chatResponseChoicesBO.getMessageRole());
		chatResponseChoicesDO.setMessageContent(chatResponseChoicesBO.getMessageContent());
		chatResponseChoicesDO.setMessageFinishReason(chatResponseChoicesBO.getMessageFinishReason());
		chatResponseChoicesDO.setCreateTime(chatResponseChoicesBO.getCreateTime());
		chatResponseChoicesDO.setUpdateTime(chatResponseChoicesBO.getUpdateTime());
		return chatResponseChoicesDO;
	}

	public static List<ChatResponseChoicesBO> toBOList(List<ChatResponseChoicesDO> doList) {
        if (CollectionUtils.isEmpty(doList)) {
			return Collections.emptyList();
		}
		List<ChatResponseChoicesBO> dtoList = Lists.newArrayList();
		for (ChatResponseChoicesDO chatResponseChoicesDO : doList) {
			if (chatResponseChoicesDO != null) {
				dtoList.add(toBO(chatResponseChoicesDO));
			}
		}
		return dtoList;
	}

	public static List<ChatResponseChoicesDO> toDOList(List<ChatResponseChoicesBO> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return Collections.emptyList();
		}

		List<ChatResponseChoicesDO> doList = Lists.newArrayList();
		for (ChatResponseChoicesBO chatResponseChoicesBO : dtoList) {
			if (chatResponseChoicesBO != null) {
				doList.add(toDO(chatResponseChoicesBO));
			}
		}
		return doList;
	}

}
