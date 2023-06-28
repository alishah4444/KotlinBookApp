package com.iaminca.openai.handler;

import com.iaminca.openai.common.Constants;
import com.iaminca.openai.service.bo.ChatResponseChoicesBO;
import com.iaminca.openai.service.ChatResponseChoicesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
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
public class ChatResponseChoicesHandler {

    private final ChatResponseChoicesService chatResponseChoicesService;

    public void addChatRequestChoicesList(List<ChatResponseChoicesBO> chatResponseChoicesList){
        log.info("Add Chat response choices: {}", Constants.GSON.toJson(chatResponseChoicesList));
        if(CollectionUtils.isEmpty(chatResponseChoicesList)){
            return;
        }
        for(ChatResponseChoicesBO chatResponseChoicesBO : chatResponseChoicesList){
            chatResponseChoicesBO.setCreateTime(new Date());
            chatResponseChoicesBO.setUpdateTime(chatResponseChoicesBO.getCreateTime());
        }
        chatResponseChoicesService.addList(chatResponseChoicesList);
    }

}
