package com.iaminca.openai.service;

import org.springframework.http.codec.multipart.FilePart;

public interface StorageService {

    String store(FilePart file);
}
