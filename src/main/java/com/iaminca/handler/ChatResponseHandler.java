package com.iaminca.handler;

import com.iaminca.common.Constants;
import com.iaminca.service.ChatResponseService;
import com.iaminca.service.bo.ChatResponseBO;
import com.iaminca.service.bo.ChatResponseChoicesBO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
@RequiredArgsConstructor
public class ChatResponseHandler {

    private final ChatResponseService chatResponseService;
    private final ChatResponseChoicesHandler chatResponseChoicesHandler;

    public void addChatRequest(ChatResponseBO chatResponseBO, List<ChatResponseChoicesBO> chatResponseChoicesList){
        log.info("Add Chat response: {}", Constants.GSON.toJson(chatResponseBO));
        ChatResponseBO chatResponseNew = chatResponseService.add(chatResponseBO);
        for(ChatResponseChoicesBO chatResponseChoicesBO :chatResponseChoicesList){
            chatResponseChoicesBO.setResponseId(chatResponseNew.getId());
        }

        chatResponseChoicesHandler.addChatRequestChoicesList(chatResponseChoicesList);
    }

}
