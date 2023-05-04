package com.iaminca.service.covert;

import com.google.common.collect.Lists;
import com.iaminca.dal.dataobject.ChatRequestDO;
import com.iaminca.service.bo.ChatRequestBO;
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
public class ChatRequestConvert {

	public static ChatRequestBO toBO(ChatRequestDO chatRequestDO) {
		if (chatRequestDO == null) {
			return null;
		}
		ChatRequestBO chatRequestBO = new ChatRequestBO();
		chatRequestBO.setUserId(chatRequestDO.getUserId());
		chatRequestBO.setChatModel(chatRequestDO.getChatModel());
		chatRequestBO.setChatContent(chatRequestDO.getChatContent());
		chatRequestBO.setChatMaxToken(chatRequestDO.getChatMaxToken());
		chatRequestBO.setChatMaxNumber(chatRequestDO.getChatMaxNumber());
		return chatRequestBO;
	}

	public static ChatRequestDO toDO(ChatRequestBO chatRequestBO) {
		if (chatRequestBO == null) {
			return null;
		}
		ChatRequestDO chatRequestDO = new ChatRequestDO();
		chatRequestDO.setUserId(chatRequestBO.getUserId());
		chatRequestDO.setChatModel(chatRequestBO.getChatModel());
		chatRequestDO.setChatContent(chatRequestBO.getChatContent());
		chatRequestDO.setChatMaxToken(chatRequestBO.getChatMaxToken());
		chatRequestDO.setChatMaxNumber(chatRequestBO.getChatMaxNumber());
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
