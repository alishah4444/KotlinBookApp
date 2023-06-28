package com.iaminca.openai.web.convert;

import com.google.common.collect.Lists;
import com.iaminca.openai.service.bo.ChatRequestMessageBO;
import com.iaminca.openai.web.dto.ChatMessageDTO;
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
public class ChatCompletionMessageRequestDTOConvert {

	public static ChatRequestMessageBO toGptBO(ChatMessageDTO chatRequestBO) {
		if (chatRequestBO == null) {
			return null;
		}
		ChatRequestMessageBO chatRequestMessageBO = new ChatRequestMessageBO();
		chatRequestMessageBO.setRole(chatRequestBO.getRole());
		chatRequestMessageBO.setContent(chatRequestBO.getContent());
		return chatRequestMessageBO;
	}


	public static List<ChatRequestMessageBO> toBOList(List<ChatMessageDTO> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
			return Collections.emptyList();
		}
		List<ChatRequestMessageBO> boList = Lists.newArrayList();
		for (ChatMessageDTO chatMessageDTO : dtoList) {
			if (chatMessageDTO != null) {
				boList.add(toGptBO(chatMessageDTO));
			}
		}
		return boList;
	}

}
