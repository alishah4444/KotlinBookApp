/*
 * Copyright 2017 ZJNU ACM.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.iaminca.exception;

import com.iaminca.common.Constants;
import com.iaminca.common.ErrorCode;
import com.iaminca.common.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class BusinessExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResultModel messageExceptionHandler(RuntimeException ex) {
        log.info("统一异常处理：{}", ex);
        ResultModel result = new ResultModel();

        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            result.setCode(businessException.getErrorCode().getCode());
            result.setMessage(businessException.getMessage());
            return result;
        }
        result.setCode(ErrorCode.UNKNOW_ERROR.getCode());
        result.setMessage(ErrorCode.UNKNOW_ERROR.getMessage());
        result.setData("FAILED");
        log.info("统一异常处理返回：{}", Constants.GSON.toJson(result));
        return result;
    }
}
