package com.iaminca.openai.web.controller;

import com.iaminca.openai.common.ResultModel;
import com.iaminca.openai.handler.UserKeywordsHandler;
import com.iaminca.openai.web.controller.base.UserBaseController;
import com.iaminca.openai.web.convert.UserKeywordsConvert;
import com.iaminca.openai.web.dto.UserKeywordsDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auto")
@Slf4j
public class UserAuthorizingController extends UserBaseController {

    @Resource
    private UserKeywordsHandler userKeyWordsHandler;


    /**
     * Authorize to auto system.
     * @param token
     * @param userKeywordsDTO
     * @return
     */
    @PostMapping("/authorizing")
    public ResultModel applyKey(@RequestHeader(name = "token")String token,@RequestBody UserKeywordsDTO userKeywordsDTO) {
        Long userID = getUserID(token);
        userKeywordsDTO.setUserId(userID);
        userKeyWordsHandler.authorizing(UserKeywordsConvert.toBO(userKeywordsDTO));
        return new ResultModel();
    }


}
