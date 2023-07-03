package com.iaminca.openai.web.controller;

import com.iaminca.openai.common.ResultModel;
import com.iaminca.openai.common.model.PageListResult;
import com.iaminca.openai.handler.UserTasksInfoHandler;
import com.iaminca.openai.query.UserTaskInfoQuery;
import com.iaminca.openai.service.bo.UserTaskInfoBO;
import com.iaminca.openai.web.controller.base.UserBaseController;
import com.iaminca.openai.web.convert.UserTaskInfoConvert;
import com.iaminca.openai.web.dto.UserTaskInfoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
@Slf4j
public class UserTaskController extends UserBaseController {

    @Resource
    private UserTasksInfoHandler userTasksInfoHandler;


    /**
     * Find page data
     * @param token
     * @param query
     * @return
     */
    @GetMapping("/page")
    public ResultModel applyKey(@RequestHeader(name = "token")String token, UserTaskInfoQuery query) {
        Long userID = getUserID(token);
        query.setUserId(userID);
        PageListResult<UserTaskInfoBO> page = userTasksInfoHandler.findPage(query);
        return new ResultModel(page);
    }


    /**
     * Find data by Id
     * @param token
     * @param query
     * @return
     */
    @GetMapping("/findById")
    public ResultModel findById(@RequestHeader(name = "token")String token, UserTaskInfoQuery query) {
        Long userID = getUserID(token);
        query.setUserId(userID);
        PageListResult<UserTaskInfoBO> page = userTasksInfoHandler.findPage(query);
        if(page == null || CollectionUtils.isEmpty(page.getList())){
            return  new ResultModel();
        }
        return new ResultModel(UserTaskInfoConvert.toDTO(page.getList().get(0)));
    }


    /**
     * Adding
     * @param token
     * @param userTaskInfoDTO
     * @return
     */
    @PostMapping("/add")
    public ResultModel add(@RequestHeader(name = "token")String token,@RequestBody UserTaskInfoDTO userTaskInfoDTO) {
        Long userID = getUserID(token);
        userTaskInfoDTO.setUserId(userID);
        userTasksInfoHandler.insert(UserTaskInfoConvert.toBO(userTaskInfoDTO));
        return new ResultModel();
    }


    /**
     * Updating
     * @param token
     * @param userTaskInfoDTO
     * @return
     */
    @PostMapping("/update")
    public ResultModel update(@RequestHeader(name = "token")String token,@RequestBody UserTaskInfoDTO userTaskInfoDTO) {
        Long userID = getUserID(token);
        userTaskInfoDTO.setUserId(userID);
        userTasksInfoHandler.updateById(UserTaskInfoConvert.toBO(userTaskInfoDTO));
        return new ResultModel();
    }


    /**
     * del
     * @param token
     * @param userTaskInfoDTO
     * @return
     */
    @PostMapping("/del")
    public ResultModel del(@RequestHeader(name = "token")String token,@RequestBody UserTaskInfoDTO userTaskInfoDTO) {
        Long userID = getUserID(token);
        userTaskInfoDTO.setUserId(userID);
        userTasksInfoHandler.delById(UserTaskInfoConvert.toBO(userTaskInfoDTO));
        return new ResultModel();
    }




}
