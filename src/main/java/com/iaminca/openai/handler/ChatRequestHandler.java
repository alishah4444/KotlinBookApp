package com.iaminca.openai.handler;

import com.iaminca.openai.common.Constants;
import com.iaminca.openai.common.DelFlagEnum;
import com.iaminca.openai.service.bo.ChatRequestBO;
import com.iaminca.openai.service.bo.ChatRequestMessageBO;
import com.iaminca.openai.service.ChatRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final ChatRequestService chatRequestService;
    private final ChatRequestMessagesHandler chatRequestMessagesHandler;

    public ChatRequestBO addChatRequest(ChatRequestBO chatRequestBO){
        log.info("Add Chat request: {}", Constants.GSON.toJson(chatRequestBO));
        chatRequestBO.setDelFlag(DelFlagEnum.NOT_DEL.getCode());
        ChatRequestBO requestBO = chatRequestService.add(chatRequestBO);
        for(ChatRequestMessageBO chatRequestMessageBO: chatRequestBO.getMessages()){
            chatRequestMessageBO.setChatRequestId(requestBO.getId());
        }
        chatRequestMessagesHandler.addChatRequestMessage(chatRequestBO.getMessages());
        return requestBO;
    }

}
