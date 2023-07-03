package com.iaminca.openai.web.controller;

import com.iaminca.openai.common.ResultModel;
import com.iaminca.openai.common.model.PageListResult;
import com.iaminca.openai.handler.UserKeywordsHandler;
import com.iaminca.openai.query.UserKeywordsQuery;
import com.iaminca.openai.service.bo.UserKeywordsBO;
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


    /**
     * Authorize to auto system for pushing.
     * @param token
     * @param userKeywordsDTO
     * @return
     */
    @PostMapping("/authorizingPush")
    public ResultModel authorizingPush(@RequestHeader(name = "token")String token,@RequestBody UserKeywordsDTO userKeywordsDTO) {
        Long userID = getUserID(token);
        userKeywordsDTO.setUserId(userID);
        userKeywordsDTO.setId(userKeywordsDTO.getId());
        userKeyWordsHandler.authorizingForPushing(UserKeywordsConvert.toBO(userKeywordsDTO));
        return new ResultModel();
    }




    /**
     * Find page data
     * @param token
     * @param query
     * @return
     */
    @GetMapping("/page")
    public ResultModel page(@RequestHeader(name = "token")String token, UserKeywordsQuery query) {
        Long userID = getUserID(token);
        query.setUserId(userID);
        PageListResult<UserKeywordsBO> pageBO = userKeyWordsHandler.findPage(query);
        PageListResult<UserKeywordsDTO> pageDTO = new PageListResult(UserKeywordsConvert.toDTOList(pageBO.getList()),
                pageBO.getPageNum(),pageBO.getPageSize(),pageBO.getTotal());
        return new ResultModel(pageDTO);
    }


    /**
     * Find one data
     * @param token
     * @param queryParam
     * @return
     */
    @GetMapping("/findById")
    public ResultModel findById(@RequestHeader(name = "token")String token, UserKeywordsQuery queryParam) {
        Long userID = getUserID(token);
        UserKeywordsQuery query =  new UserKeywordsQuery();
        query.setUserId(userID);
        query.setId(queryParam.getId());
        UserKeywordsBO resultBO = userKeyWordsHandler.findOne(query);
        return new ResultModel(UserKeywordsConvert.toDTO(resultBO));
    }


}
