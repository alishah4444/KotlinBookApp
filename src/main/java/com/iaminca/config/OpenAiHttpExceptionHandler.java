package com.iaminca.config;

import com.theokanning.openai.OpenAiError;
import com.theokanning.openai.OpenAiHttpException;
import okhttp3.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import retrofit2.HttpException;
import retrofit2.Response;

import java.io.IOException;

@ControllerAdvice
public class OpenAiHttpExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<OpenAiError> handle(OpenAiHttpException ex) {
        OpenAiError oae = new OpenAiError();
        oae.error = new OpenAiError.OpenAiErrorDetails();
        oae.error.setMessage(ex.getMessage());
        oae.error.setType(ex.type);
        oae.error.setParam(ex.param);
        oae.error.setCode(ex.code);
        return ResponseEntity.status(ex.statusCode).body(oae);
    }

    @ExceptionHandler
    public ResponseEntity<?> handle(HttpException ex) throws IOException {
        ResponseEntity.BodyBuilder builder = ResponseEntity.status(ex.code());
        Response<?> response = ex.response();
        if (response != null) {
            builder.headers(headers -> headers.putAll(response.headers().toMultimap()));
            try (ResponseBody responseBody = response.errorBody()) {
                if (responseBody != null) {
                    return builder.body(responseBody.bytes());
                }
            }
        }
        return builder.build();
    }

}