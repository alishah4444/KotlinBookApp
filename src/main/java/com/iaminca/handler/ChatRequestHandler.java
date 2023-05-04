package com.iaminca.handler;

import com.iaminca.common.Constants;
import com.iaminca.common.DelFlagEnum;
import com.iaminca.service.ChatRequestService;
import com.iaminca.service.bo.ChatRequestBO;
import com.iaminca.service.bo.ChatRequestMessageBO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:22
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class ChatRequestHandler {

    @Autowired
    private ChatRequestService chatRequestService;

    @Autowired
    private ChatRequestMessagesHandler chatRequestMessagesHandler;

    public void addChatRequest(ChatRequestBO chatRequestBO){
        log.info("Add Chat request: {}", Constants.GSON.toJson(chatRequestBO));
        chatRequestBO.setDelFlag(DelFlagEnum.NOT_DEL.getCode());
        ChatRequestBO requestBO = chatRequestService.add(chatRequestBO);
        for(ChatRequestMessageBO chatRequestMessageBO: chatRequestBO.getMessages()){
            chatRequestMessageBO.setChatRequestId(requestBO.getId());
        }
        chatRequestMessagesHandler.addChatRequestMessage(chatRequestBO.getMessages());
    }

}
