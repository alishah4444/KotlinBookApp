package com.iaminca.service.covert;


import com.google.common.collect.Lists;
import com.iaminca.dal.dataobject.ChatResponseDO;
import com.iaminca.service.bo.ChatResponseBO;
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
public class ChatResponseConvert {

	public static ChatResponseBO toBO(ChatResponseDO chatResponseDO) {
		if (chatResponseDO == null) {
			return null;
		}
		ChatResponseBO chatResponseBO = new ChatResponseBO();
		chatResponseBO.setUserId(chatResponseDO.getUserId());
		chatResponseBO.setChatResponseId(chatResponseDO.getChatResponseId());
		chatResponseBO.setChatModel(chatResponseDO.getChatModel());
		chatResponseBO.setChatObject(chatResponseDO.getChatObject());
		chatResponseBO.setChatCreated(chatResponseDO.getChatCreated());
		chatResponseBO.setChatUsagePromptTokens(chatResponseDO.getChatUsagePromptTokens());
		chatResponseBO.setChatUsageCompletionTokens(chatResponseDO.getChatUsageCompletionTokens());
		chatResponseBO.setChatUsageTotalTokens(chatResponseDO.getChatUsageTotalTokens());
		return chatResponseBO;
	}

	public static ChatResponseDO toDO(ChatResponseBO chatResponseBO) {
		if (chatResponseBO == null) {
			return null;
		}
		ChatResponseDO chatResponseDO = new ChatResponseDO();
		chatResponseDO.setUserId(chatResponseBO.getUserId());
		chatResponseDO.setChatResponseId(chatResponseBO.getChatResponseId());
		chatResponseDO.setChatModel(chatResponseBO.getChatModel());
		chatResponseDO.setChatObject(chatResponseBO.getChatObject());
		chatResponseDO.setChatCreated(chatResponseBO.getChatCreated());
		chatResponseDO.setChatUsagePromptTokens(chatResponseBO.getChatUsagePromptTokens());
		chatResponseDO.setChatUsageCompletionTokens(chatResponseBO.getChatUsageCompletionTokens());
		chatResponseDO.setChatUsageTotalTokens(chatResponseBO.getChatUsageTotalTokens());
		return chatResponseDO;
	}

	public static List<ChatResponseBO> toBOList(List<ChatResponseDO> doList) {
        if (CollectionUtils.isEmpty(doList)) {
			return Collections.emptyList();
		}
		List<ChatResponseBO> dtoList = Lists.newArrayList();
		for (ChatResponseDO chatResponseDO : doList) {
			if (chatResponseDO != null) {
				dtoList.add(toBO(chatResponseDO));
			}
		}
		return dtoList;
	}

	public static List<ChatResponseDO> toDOList(List<ChatResponseBO> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return Collections.emptyList();
		}

		List<ChatResponseDO> doList = Lists.newArrayList();
		for (ChatResponseBO chatResponseBO : dtoList) {
			if (chatResponseBO != null) {
				doList.add(toDO(chatResponseBO));
			}
		}
		return doList;
	}

}
