package com.iaminca.handler;

import com.iaminca.common.Constants;
import com.iaminca.service.ChatRequestService;
import com.iaminca.service.UserService;
import com.iaminca.service.bo.ChatRequestBO;
import com.iaminca.service.bo.UserBO;
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
public class ChatRequestHandler {

    @Autowired
    private ChatRequestService chatRequestService;

    public void addChatRequest(ChatRequestBO chatRequestBO){
        log.info("Add Chat request: {}", Constants.GSON.toJson(chatRequestBO));
        chatRequestService.add(chatRequestBO);
    }

}
