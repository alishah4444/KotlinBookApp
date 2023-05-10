package com.iaminca.web.controller.exexception;

import com.iaminca.common.Constants;
import com.iaminca.web.controller.OpenAIController;
import com.theokanning.openai.OpenAiError;
import com.theokanning.openai.OpenAiHttpException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ResponseBody;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import retrofit2.HttpException;
import retrofit2.Response;

import java.io.IOException;

/**
 * @link <a href="https://platform.openai.com/docs/guides/error-codes/api-errors">OpenAI API - Error codes</a>
 */
@Order(Ordered.HIGHEST_PRECEDENCE) // make the handler high precedence than global BusinessExceptionHandler
@RestControllerAdvice(assignableTypes = OpenAIController.class)
@Slf4j
public class OpenAiHttpExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<OpenAiError> handle(OpenAiHttpException ex) {
        log.info("Open AI Exception ======");
        OpenAiError oae = new OpenAiError();
        oae.error = new OpenAiError.OpenAiErrorDetails();
        oae.error.setMessage(ex.getMessage());
        oae.error.setType(ex.type);
        oae.error.setParam(ex.param);
        oae.error.setCode(ex.code);
        log.info("Open AI Exception ======: {}", Constants.GSON.toJson(oae));
        return ResponseEntity.status(ex.statusCode).body(oae);
    }

    @ExceptionHandler
    public ResponseEntity<?> handle(HttpException ex) throws IOException {
        ResponseEntity.BodyBuilder builder = ResponseEntity.status(ex.code());
        Response<?> response = ex.response();
        if (response != null) {
            builder.headers(headers -> headers.putAll(response.headers().toMultimap()));
            //noinspection resource
            ResponseBody responseBody = response.errorBody();
            if (responseBody != null) {
                return builder.body(responseBody.bytes());
            }
        }
        return builder.build();
    }

}
