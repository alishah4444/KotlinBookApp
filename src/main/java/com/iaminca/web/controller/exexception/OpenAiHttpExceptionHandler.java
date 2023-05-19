package com.iaminca.web.controller.exexception;

import com.fasterxml.jackson.core.JacksonException;
import com.google.common.base.Throwables;
import com.iaminca.common.Constants;
import com.iaminca.web.controller.OpenAIController;
import com.theokanning.openai.OpenAiError;
import com.theokanning.openai.OpenAiHttpException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ResponseBody;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebInputException;
import retrofit2.HttpException;
import retrofit2.Response;

import java.io.IOException;
import java.util.Locale;

/**
 * @link <a href="https://platform.openai.com/docs/guides/error-codes/api-errors">OpenAI API - Error codes</a>
 */
@Order(Ordered.HIGHEST_PRECEDENCE) // make the handler high precedence than global BusinessExceptionHandler
@RestControllerAdvice(assignableTypes = OpenAIController.class)
@Slf4j
@RequiredArgsConstructor
public class OpenAiHttpExceptionHandler {

    private final MessageSource messageSource;

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

    /**
     * this handler also handles {@link ServerWebInputException}
     */
    @ExceptionHandler
    public ResponseEntity<OpenAiError> handle(ResponseStatusException ex, Locale locale) {
        String type = ex instanceof ServerWebInputException ? "invalid_request_error" : null;
        ResponseEntity.BodyBuilder builder = ResponseEntity.status(ex.getStatus());
        OpenAiError oae = new OpenAiError();
        oae.error = new OpenAiError.OpenAiErrorDetails();
        if (Throwables.getRootCause(ex) instanceof JacksonException) {
            oae.error.setMessage(messageSource.getMessage("jackson.parse.error", null, locale));
        } else {
            oae.error.setMessage(ex.getReason());
        }
        oae.error.setType(type);
        return builder.body(oae);
    }
}
