package com.iaminca.openai.service.impl;

import com.iaminca.openai.service.OpenAiMultipartService;
import com.theokanning.openai.OpenAiApi;
import com.theokanning.openai.file.File;
import com.theokanning.openai.image.CreateImageEditRequest;
import com.theokanning.openai.image.CreateImageVariationRequest;
import com.theokanning.openai.image.ImageResult;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OpenAiMultipartServiceImpl implements OpenAiMultipartService {

    private final OpenAiApi api;

    private Mono<MultipartBody.Part> toFormDataPart(MediaType fallbackMediaType, String name, FilePart filePart) {
        MediaType mediaType = Optional.ofNullable(filePart.headers().getContentType())
                .map(Object::toString).map(MediaType::parse).orElse(fallbackMediaType);
        return filePart.content().collectList().map(buffers -> new CollectedRequestBody(mediaType, buffers))
                .map(body -> MultipartBody.Part.createFormData(name, filePart.filename(), body));
    }

    public Mono<File> uploadFile(String purpose, FilePart serverFile) {
        RequestBody purposeBody = RequestBody.create(purpose, MultipartBody.FORM);
        return toFormDataPart(MediaType.parse("text"), "file", serverFile)
                .map(file -> OpenAiService.execute(api.uploadFile(purposeBody, file)));
    }

    public Mono<ImageResult> createImageEdit(CreateImageEditRequest request, FilePart serverImage, @Nullable FilePart serverMask) {
        MediaType imageType = MediaType.parse("image");
        return Mono.zip(
                toFormDataPart(imageType, "image", serverImage),
                serverMask != null ?
                        toFormDataPart(imageType, "mask", serverMask).map(Optional::of) :
                        Mono.just(Optional.<MultipartBody.Part>empty())
        ).map(tuple -> {
            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MediaType.get("multipart/form-data"))
                    .addFormDataPart("prompt", request.getPrompt())
                    .addFormDataPart("size", request.getSize())
                    .addFormDataPart("response_format", request.getResponseFormat())
                    .addPart(tuple.getT1());

            if (request.getN() != null) {
                builder.addFormDataPart("n", request.getN().toString());
            }
            tuple.getT2().ifPresent(builder::addPart);
            if (request.getUser() != null) {
                builder.addFormDataPart("user", request.getUser());
            }
            return OpenAiService.execute(api.createImageEdit(builder.build()));
        });
    }

    public Mono<ImageResult> createImageVariation(CreateImageVariationRequest request, FilePart image) {
        return toFormDataPart(MediaType.parse("image"), "image", image).map(imageBody -> {
            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MediaType.get("multipart/form-data"))
                    .addFormDataPart("size", request.getSize())
                    .addFormDataPart("response_format", request.getResponseFormat())
                    .addPart(imageBody);

            if (request.getN() != null) {
                builder.addFormDataPart("n", request.getN().toString());
            }
            if (request.getUser() != null) {
                builder.addFormDataPart("user", request.getUser());
            }
            return OpenAiService.execute(api.createImageVariation(builder.build()));
        });
    }

    private static class CollectedRequestBody extends RequestBody {

        private final MediaType mediaType;
        private final List<DataBuffer> buffers;

        public CollectedRequestBody(MediaType mediaType, List<DataBuffer> buffers) {
            this.mediaType = mediaType;
            this.buffers = buffers;
        }

        @Override
        public MediaType contentType() {
            return mediaType;
        }

        @Override
        public long contentLength() {
            return buffers.stream().mapToLong(DataBuffer::readableByteCount).sum();
        }

        @Override
        public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {
            for (DataBuffer buffer : buffers) bufferedSink.write(buffer.asByteBuffer());
        }
    }
}
