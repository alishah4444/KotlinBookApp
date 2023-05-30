package com.iaminca.web.controller;

import com.iaminca.common.Constants;
import com.iaminca.common.ErrorCode;
import com.iaminca.common.ResultModel;
import com.iaminca.exception.BusinessException;
import com.iaminca.handler.ChatHandler;
import com.iaminca.handler.UserHandler;
import com.iaminca.service.bo.*;
import com.iaminca.web.controller.base.UserBaseController;
import com.iaminca.web.dto.ChatCompletionTestChatRequestDTO;
import com.iaminca.web.dto.UserLoginDTO;
import com.iaminca.web.dto.UserMessageDTO;
import com.iaminca.web.dto.UserRegisterDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController extends UserBaseController {

    @Resource
    private UserHandler userHandler;
    @Resource
    private final ChatHandler chatHandler;

    @PostMapping("/register")
    public ResultModel register(@RequestBody UserRegisterDTO userRegisterDTO) {
        if (ObjectUtils.isEmpty(userRegisterDTO) || ObjectUtils.isEmpty(userRegisterDTO.getUserPhone()) ||
                ObjectUtils.isEmpty(userRegisterDTO.getVerificationCode()) || ObjectUtils.isEmpty(userRegisterDTO.getPassword())) {
            throw new BusinessException(ErrorCode.PARAM_IS_ERROR);
        }
        UserRegisterBO userRegisterBO = new UserRegisterBO();
        userRegisterBO.setUserPhone(userRegisterDTO.getUserPhone());
        userRegisterBO.setVerificationCode(userRegisterDTO.getVerificationCode());
        userRegisterBO.setPassword(userRegisterDTO.getPassword());
        String token = userHandler.chekVerificationCode(userRegisterBO);
        return new ResultModel(token);
    }

    //TODO 现在token有重复的，加入新的token之前，要把老的token删除。
    @PostMapping("/login")
    public ResultModel login(@RequestBody UserLoginDTO userLoginDTO) {
        if (ObjectUtils.isEmpty(userLoginDTO) || ObjectUtils.isEmpty(userLoginDTO.getUserPhone())
                || ObjectUtils.isEmpty(userLoginDTO.getPassword())) {
            throw new BusinessException(ErrorCode.PARAM_IS_ERROR);
        }
        UserLoginBO userLoginBO = new UserLoginBO();
        userLoginBO.setUserPhone(userLoginDTO.getUserPhone());
        userLoginBO.setPassword(userLoginDTO.getPassword());
        String token = userHandler.passwordLogin(userLoginBO);
        return new ResultModel(token);
    }

    @PostMapping("/sendCode")
    public ResultModel sendCode(@RequestBody UserRegisterDTO userRegisterDTO) {
        UserBO userBO = new UserBO();
        userBO.setUserPhone(userRegisterDTO.getUserPhone());
        userHandler.sendCode(userBO);
        return new ResultModel();
    }


    @PostMapping("/myMessage")
    public ResultModel myMessage(@RequestHeader(name = "token") String token) {
        UserBO user = getUser(token);
        Long userBalance = userHandler.findUserBalance(user.getId());
        UserMessageDTO userMessageDTO = new UserMessageDTO();
        userMessageDTO.setUserPhone(user.getUserPhone());
        userMessageDTO.setBalance(userBalance);
        return new ResultModel(userMessageDTO);
    }

    @PostMapping("/chatTest")
    public ResponseEntity<?> chatCompletion(@RequestBody ChatCompletionTestChatRequestDTO requestDTO, @RequestHeader(name = "token") String token) {
        ChatRequestBO request = new ChatRequestBO();
        getUserIDByGptKey(requestDTO.getGptKey());
        Long userID = getUserID(token);
        request.setUserId(userID);
        request.setGptKey(requestDTO.getGptKey());
        request.setStream(true);
        List<ChatRequestMessageBO> messages = new ArrayList<>();
        ChatRequestMessageBO chatRequestMessageBO = new ChatRequestMessageBO();
        chatRequestMessageBO.setContent(requestDTO.getMessage());
        chatRequestMessageBO.setRole(Constants.GPT_CHAT_ROLE);
        messages.add(chatRequestMessageBO);
        request.setMessages(messages);

        return ResponseEntity.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(Mono.just(request).flatMapMany(chatHandler::streamChatCompletion));
    }

}
