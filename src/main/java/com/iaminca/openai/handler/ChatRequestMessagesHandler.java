package com.iaminca.openai.handler;

import com.iaminca.openai.common.Constants;
import com.iaminca.openai.common.DelFlagEnum;
import com.iaminca.openai.service.ChatRequestMessageService;
import com.iaminca.openai.service.bo.ChatRequestMessageBO;
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
public class ChatRequestMessagesHandler {

    private final ChatRequestMessageService chatRequestMessageService;

    public void addChatRequestMessage(List<ChatRequestMessageBO> chatRequestMessageBOList){
        log.info("Add Chat request messages: {}", Constants.GSON.toJson(chatRequestMessageBOList));
        if(CollectionUtils.isEmpty(chatRequestMessageBOList)){
            return;
        }
        for(ChatRequestMessageBO chatRequestMessageBO : chatRequestMessageBOList){
            chatRequestMessageBO.setDelFlag(DelFlagEnum.NOT_DEL.getCode());
            chatRequestMessageBO.setCreateTime(new Date());
            chatRequestMessageBO.setUpdateTime(chatRequestMessageBO.getCreateTime());
        }
        chatRequestMessageService.add(chatRequestMessageBOList);
    }

}
