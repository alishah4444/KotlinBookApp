package com.iaminca.web.controller;

import com.iaminca.common.ErrorCode;
import com.iaminca.exception.BusinessException;
import com.iaminca.web.controller.base.UserBaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userFile")
@Slf4j
public class UserFileController extends UserBaseController {

    private List<String> imageList = Arrays.asList("jpg","jpeg","png");
    private String FILE_PATH="/home/openai/upload-files/";


    @PostMapping("/v1/saveImage")
    public Mono<Object> uploadFile(@RequestPart("file") FilePart file) {
        String extension = FilenameUtils.getExtension(file.filename());
        if(!imageList.contains(extension.toLowerCase())){
            throw new BusinessException(ErrorCode.USER_FILE_SUFFIX_ERROR);
        }
        Path target = Paths.get(FILE_PATH, System.currentTimeMillis()+"."+extension);

        return file.transferTo(target).then(Mono.fromCallable(() -> {
            // save to db
            return FILE_PATH+target.getFileName().toString();
        }));
    }

}
