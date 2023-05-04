package com.iaminca.service.covert;


import com.google.common.collect.Lists;
import com.iaminca.dal.dataobject.ChatResponseChoicesDO;
import com.iaminca.service.bo.ChatResponseChoicesBO;
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
		chatResponseChoicesBO.setChatResponseId(chatResponseChoicesDO.getChatResponseId());
		chatResponseChoicesBO.setChoicesIndex(chatResponseChoicesDO.getChoicesIndex());
		chatResponseChoicesBO.setChoicesMessageRole(chatResponseChoicesDO.getChoicesMessageRole());
		chatResponseChoicesBO.setChoicesMessageContent(chatResponseChoicesDO.getChoicesMessageContent());
		chatResponseChoicesBO.setChoicesMessageFinishReason(chatResponseChoicesDO.getChoicesMessageFinishReason());
		return chatResponseChoicesBO;
	}

	public static ChatResponseChoicesDO toDO(ChatResponseChoicesBO chatResponseChoicesBO) {
		if (chatResponseChoicesBO == null) {
			return null;
		}
		ChatResponseChoicesDO chatResponseChoicesDO = new ChatResponseChoicesDO();
		chatResponseChoicesDO.setId(chatResponseChoicesBO.getId());
		chatResponseChoicesDO.setChatResponseId(chatResponseChoicesBO.getChatResponseId());
		chatResponseChoicesDO.setChoicesIndex(chatResponseChoicesBO.getChoicesIndex());
		chatResponseChoicesDO.setChoicesMessageRole(chatResponseChoicesBO.getChoicesMessageRole());
		chatResponseChoicesDO.setChoicesMessageContent(chatResponseChoicesBO.getChoicesMessageContent());
		chatResponseChoicesDO.setChoicesMessageFinishReason(chatResponseChoicesBO.getChoicesMessageFinishReason());
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
