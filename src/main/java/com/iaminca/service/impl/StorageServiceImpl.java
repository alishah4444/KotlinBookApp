package com.iaminca.service.impl;

import com.iaminca.common.ErrorCode;
import com.iaminca.exception.BusinessException;
import com.iaminca.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
@Slf4j
public class StorageServiceImpl implements StorageService {

    private final Path rootLocation;

    @Autowired
    public StorageServiceImpl() {
        this.rootLocation = Paths.get("/files");
    }

    @Override
    public String store(FilePart file) {
        Path destinationFile = this.rootLocation.resolve(
                        Paths.get("/files/"))
                .normalize().toAbsolutePath();
        Mono<Void> voidMono = file.transferTo(destinationFile);
        voidMono.block();
        return destinationFile.getFileName().toString();
    }
}
