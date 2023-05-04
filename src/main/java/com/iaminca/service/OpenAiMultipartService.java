package com.iaminca.service;

import com.theokanning.openai.file.File;
import com.theokanning.openai.image.CreateImageEditRequest;
import com.theokanning.openai.image.CreateImageVariationRequest;
import com.theokanning.openai.image.ImageResult;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.lang.Nullable;
import reactor.core.publisher.Mono;

public interface OpenAiMultipartService {
    Mono<File> uploadFile(String purpose, FilePart serverFile);

    Mono<ImageResult> createImageEdit(CreateImageEditRequest request, FilePart serverImage, @Nullable FilePart serverMask);

    Mono<ImageResult> createImageVariation(CreateImageVariationRequest request, FilePart image);
}
