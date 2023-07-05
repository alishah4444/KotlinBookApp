package com.iaminca.openai.web.controller;

import com.iaminca.openai.common.Constants;
import com.iaminca.openai.common.ResultModel;
import com.iaminca.openai.web.controller.base.UserBaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/common")
@Slf4j
public class CommoneController extends UserBaseController {



    @PostMapping("/downloadFile")
    public ResultModel downloadFile(@RequestHeader(name = "token")String token) {
        getUserID(token);
        return new ResultModel(Constants.WORDPRESS_FILE_URL);
    }

    @PostMapping("/downloadSQLFile")
    public ResultModel downloadSQLFile(@RequestHeader(name = "token")String token) {
        getUserID(token);
        return new ResultModel(Constants.WORDPRESS_SQL_FILE_URL);
    }


}
