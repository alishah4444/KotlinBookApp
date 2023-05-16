package com.iaminca.service;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    String store(FilePart file);
}
