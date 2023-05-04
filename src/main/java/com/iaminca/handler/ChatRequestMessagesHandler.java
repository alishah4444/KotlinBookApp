package com.iaminca.handler;

import com.iaminca.common.Constants;
import com.iaminca.service.ChatRequestMessageService;
import com.iaminca.service.bo.ChatRequestMessageBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 
 * 
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:22
 */
@Component
@Slf4j
public class ChatRequestMessagesHandler {

    @Autowired
    private ChatRequestMessageService chatRequestMessageService;

    public void addChatRequestMessage(List<ChatRequestMessageBO> chatRequestMessageBOList){
        log.info("Add Chat request messages: {}", Constants.GSON.toJson(chatRequestMessageBOList));
        if(CollectionUtils.isEmpty(chatRequestMessageBOList)){
            return;
        }
        chatRequestMessageService.add(chatRequestMessageBOList);
    }

}
