package com.iaminca.openai.filter;

import com.iaminca.openai.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

@Slf4j
@Component
public class LogMdcFilter implements Filter {
    @Override
    public boolean isLoggable(LogRecord record) {
        log.info("LOG Filter : {}", Constants.GSON.toJson(record));
        return true;
    }
}
