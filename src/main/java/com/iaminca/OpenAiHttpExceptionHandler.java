package com.iaminca;

import com.theokanning.openai.OpenAiError;
import com.theokanning.openai.OpenAiHttpException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

}
