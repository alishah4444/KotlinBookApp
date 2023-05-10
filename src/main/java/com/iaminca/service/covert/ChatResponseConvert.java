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
		chatResponseBO.setId(chatResponseDO.getId());
		chatResponseBO.setUserId(chatResponseDO.getUserId());
		chatResponseBO.setKeyId(chatResponseDO.getKeyId());
		chatResponseBO.setRecordId(chatResponseDO.getRecordId());
		chatResponseBO.setChatResponseId(chatResponseDO.getChatResponseId());
		chatResponseBO.setModel(chatResponseDO.getModel());
		chatResponseBO.setObject(chatResponseDO.getObject());
		chatResponseBO.setCreated(chatResponseDO.getCreated());
		chatResponseBO.setUsagePromptTokens(chatResponseDO.getUsagePromptTokens());
		chatResponseBO.setUsageCompletionTokens(chatResponseDO.getUsageCompletionTokens());
		chatResponseBO.setUsageTotalTokens(chatResponseDO.getUsageTotalTokens());
		chatResponseBO.setDelFlag(chatResponseDO.getDelFlag());
		chatResponseBO.setCreateTime(chatResponseDO.getCreateTime());
		chatResponseBO.setUpdateTime(chatResponseDO.getUpdateTime());
		return chatResponseBO;
	}

	public static ChatResponseDO toDO(ChatResponseBO chatResponseBO) {
		if (chatResponseBO == null) {
			return null;
		}
		ChatResponseDO chatResponseDO = new ChatResponseDO();
		chatResponseDO.setId(chatResponseBO.getId());
		chatResponseDO.setUserId(chatResponseBO.getUserId());
		chatResponseDO.setKeyId(chatResponseBO.getKeyId());
		chatResponseDO.setRecordId(chatResponseBO.getRecordId());
		chatResponseDO.setChatResponseId(chatResponseBO.getChatResponseId());
		chatResponseDO.setModel(chatResponseBO.getModel());
		chatResponseDO.setObject(chatResponseBO.getObject());
		chatResponseDO.setCreated(chatResponseBO.getCreated());
		chatResponseDO.setUsagePromptTokens(chatResponseBO.getUsagePromptTokens());
		chatResponseDO.setUsageCompletionTokens(chatResponseBO.getUsageCompletionTokens());
		chatResponseDO.setUsageTotalTokens(chatResponseBO.getUsageTotalTokens());
		chatResponseDO.setDelFlag(chatResponseBO.getDelFlag());
		chatResponseDO.setCreateTime(chatResponseBO.getCreateTime());
		chatResponseDO.setUpdateTime(chatResponseBO.getUpdateTime());
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
