package com.iaminca.openai.web.controller;

import com.iaminca.openai.common.ErrorCode;
import com.iaminca.openai.exception.BusinessException;
import com.iaminca.openai.utils.DateUtil;
import com.iaminca.openai.web.controller.base.UserBaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/userFile")
@Slf4j
public class UserFileController extends UserBaseController {

    private static final List<String> imageList = Arrays.asList("jpg", "jpeg", "png");
    private final Path imageSavePath;

    public UserFileController(@Value("${upload.recharge.path}") String rechargeImageDir) throws IOException {
        imageSavePath = Paths.get(rechargeImageDir);
        if (!Files.isDirectory(imageSavePath)) {
            Files.createDirectories(imageSavePath);
        }
        log.info("imageSavePath: {}", rechargeImageDir);
    }

    @PostMapping("/v1/saveImage")
    public Mono<Object> uploadFile(@RequestPart("file") FilePart file) {
        String extension = FilenameUtils.getExtension(file.filename());
        if (!imageList.contains(extension.toLowerCase())) {
            throw new BusinessException(ErrorCode.USER_FILE_SUFFIX_ERROR);
        }
        String format = DateUtil.format(new Date(), DateUtil.SHORT_DATE_PATTERN_2);
        Path target = imageSavePath.resolve(format + System.nanoTime() + "." + extension);

        return file.transferTo(target).then(Mono.fromCallable(() -> {
            // save to db
            return target.getFileName().toString();
        }));
    }

}
