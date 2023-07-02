package com.iaminca.openai.web.controller;

import com.iaminca.openai.handler.ChatHandler;
import com.iaminca.openai.service.bo.ChatRequestBO;
import com.iaminca.openai.web.controller.base.OpenAIBaseController;
import com.iaminca.openai.web.convert.ChatCompletionRequestDTOConvert;
import com.iaminca.openai.web.convert.CompletionRequestConvertDTO;
import com.iaminca.openai.web.dto.ChatCompletionRequestDTO;
import com.iaminca.openai.web.dto.CompletionRequestDTO;
import com.iaminca.openai.service.OpenAiMultipartService;
import com.theokanning.openai.DeleteResult;
import com.theokanning.openai.OpenAiResponse;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.edit.EditRequest;
import com.theokanning.openai.edit.EditResult;
import com.theokanning.openai.embedding.EmbeddingRequest;
import com.theokanning.openai.embedding.EmbeddingResult;
import com.theokanning.openai.file.File;
import com.theokanning.openai.finetune.FineTuneEvent;
import com.theokanning.openai.finetune.FineTuneRequest;
import com.theokanning.openai.finetune.FineTuneResult;
import com.theokanning.openai.image.CreateImageEditRequest;
import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.image.CreateImageVariationRequest;
import com.theokanning.openai.image.ImageResult;
import com.theokanning.openai.model.Model;
import com.theokanning.openai.moderation.ModerationRequest;
import com.theokanning.openai.moderation.ModerationResult;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

@RestController
@RequiredArgsConstructor
public class OpenAIController extends OpenAIBaseController {

    private final ChatHandler chatHandler;
    private final OpenAiService openAiService;
    private final OpenAiMultipartService openAiMultipartService;

    private <Tp> OpenAiResponse<Tp> makeResponse(List<Tp> list) {
        OpenAiResponse<Tp> response = new OpenAiResponse<>();
        response.data = list;
        response.object = "list";
        return response;
    }

    @GetMapping("v1/models")
    public OpenAiResponse<Model> listModels() {
        return makeResponse(openAiService.listModels());
    }

    @GetMapping("/v1/models/{model_id}")
    public Model getModel(@PathVariable("model_id") String modelId) {
        return openAiService.getModel(modelId);
    }

    private <Request> ResponseEntity<?> selectStream(
            Boolean stream,
            Function<? super Request, ? extends Publisher<?>> ifStream,
            Function<? super Request, ?> notStream,
            Mono<Request> request) {
        if (!Boolean.TRUE.equals(stream)) {
//            HttpHeaders headers = new HttpHeaders();
//            headers.set(HttpHeaders.CONTENT_TYPE,"application/octet-stream");
//            ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.ok().headers(headers);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(request.map(notStream));
        }
        // https://platform.openai.com/docs/api-reference/completions/create#completions/create-stream
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.ok().header("X-Accel-Buffering", "no");
        return bodyBuilder.contentType(MediaType.TEXT_EVENT_STREAM).body(request.flatMapMany(ifStream).concatWithValues("[DONE]"));
    }

    @PostMapping("/v1/completions")
    public ResponseEntity<?> completion(@RequestBody CompletionRequestDTO requestDTO) {
        CompletionRequest request = CompletionRequestConvertDTO.toRequestBO(requestDTO);
        return selectStream(request.getStream(), openAiService::streamCompletion, openAiService::createCompletion, Mono.just(request));
    }

    @PostMapping("/v1/chat/completions")
    public ResponseEntity<?> chatCompletion(@RequestBody ChatCompletionRequestDTO requestDTO, @RequestHeader("Authorization")String gptKeyParam) {
        ChatRequestBO request = ChatCompletionRequestDTOConvert.toBO(requestDTO);
        String gptKey = gptKeyParam.split(" ")[1];
        Long userID = getUserID(gptKey);
        request.setUserId(userID);
        request.setGptKey(gptKey);
        return selectStream(request.getStream(), chatHandler::streamChatCompletion, chatHandler::createChatCompletion, Mono.just(request));
    }

//    @Deprecated
//    @PostMapping("/v1/engines/{engine_id}/completions")
//    public CompletionResult createCompletion(@PathVariable("engine_id") String engineId, @RequestBody CompletionRequest request) {
//    }

    @PostMapping("/v1/edits")
    public EditResult createEdit(@RequestBody EditRequest request) {
        return openAiService.createEdit(request);
    }

//    @Deprecated
//    @PostMapping("/v1/engines/{engine_id}/edits")
//    public EditResult createEdit(@PathVariable("engine_id") String var1, @RequestBody EditRequest var2) {
//    }

    @PostMapping("/v1/embeddings")
    public EmbeddingResult createEmbeddings(@RequestBody EmbeddingRequest request) {
        return openAiService.createEmbeddings(request);
    }

//    @Deprecated
//    @PostMapping("/v1/engines/{engine_id}/embeddings")
//    public EmbeddingResult createEmbeddings(@PathVariable("engine_id") String engineId, @RequestBody EmbeddingRequest request){
//    }

    @GetMapping("/v1/files")
    public OpenAiResponse<File> listFiles() {
        return makeResponse(openAiService.listFiles());
    }

    @PostMapping("/v1/files")
    public Mono<File> uploadFile(@RequestPart("purpose") String purpose, @RequestPart("file") FilePart file) {
        return openAiMultipartService.uploadFile(purpose, file);
    }

    @DeleteMapping("/v1/files/{file_id}")
    public DeleteResult deleteFile(@PathVariable("file_id") String fileId) {
        return openAiService.deleteFile(fileId);
    }

    @GetMapping("/v1/files/{file_id}")
    public File retrieveFile(@PathVariable("file_id") String fileId) {
        return openAiService.retrieveFile(fileId);
    }

    @PostMapping("/v1/fine-tunes")
    public FineTuneResult createFineTune(@RequestBody FineTuneRequest request) {
        return openAiService.createFineTune(request);
    }

    // TODO duplicate
//    @PostMapping("/v1/completions")
//    public CompletionResult createFineTuneCompletion(@RequestBody CompletionRequest request) {
//        return openAiService.createFineTuneCompletion(request);
//    }

    @GetMapping("/v1/fine-tunes")
    public OpenAiResponse<FineTuneResult> listFineTunes() {
        return makeResponse(openAiService.listFineTunes());
    }

    @GetMapping("/v1/fine-tunes/{fine_tune_id}")
    public FineTuneResult retrieveFineTune(@PathVariable("fine_tune_id") String fineTuneId) {
        return openAiService.retrieveFineTune(fineTuneId);
    }

    @PostMapping("/v1/fine-tunes/{fine_tune_id}/cancel")
    public FineTuneResult cancelFineTune(@PathVariable("fine_tune_id") String fineTuneId) {
        return openAiService.cancelFineTune(fineTuneId);
    }

    @GetMapping("/v1/fine-tunes/{fine_tune_id}/events")
    public OpenAiResponse<FineTuneEvent> listFineTuneEvents(@PathVariable("fine_tune_id") String fineTuneId) {
        return makeResponse(openAiService.listFineTuneEvents(fineTuneId));
    }

    @DeleteMapping("/v1/models/{fine_tune_id}")
    public DeleteResult deleteFineTune(@PathVariable("fine_tune_id") String fineTuneId) {
        return openAiService.deleteFineTune(fineTuneId);
    }

    @PostMapping("/v1/images/generations")
    public ImageResult createImage(@RequestBody CreateImageRequest request) {
        return openAiService.createImage(request);
    }

    @PostMapping("/v1/images/edits")
    public Mono<ImageResult> createImageEdit(
            @RequestPart("prompt") String prompt,
            @RequestPart(value = "size", required = false) String size,
            @RequestPart(value = "response_format", required = false) String responseFormat,
            @RequestPart(value = "n", required = false) Integer n,
            @RequestPart("image") FilePart image,
            @RequestPart(value = "mask", required = false) FilePart mask,
            @RequestPart(value = "user", required = false) String user) {
        CreateImageEditRequest request = CreateImageEditRequest.builder()
                .prompt(prompt).size(size).responseFormat(responseFormat).n(n).user(user)
                .build();
        return openAiMultipartService.createImageEdit(request, image, mask);
    }

    @PostMapping("/v1/images/variations")
    public Mono<ImageResult> createImageVariation(
            @RequestPart(value = "size", required = false) String size,
            @RequestPart(value = "response_format", required = false) String responseFormat,
            @RequestPart(value = "n", required = false) Integer n,
            @RequestPart("image") FilePart image,
            @RequestPart(value = "user", required = false) String user) {
        CreateImageVariationRequest request = CreateImageVariationRequest.builder()
                .size(size).responseFormat(responseFormat).n(n).user(user)
                .build();
        return openAiMultipartService.createImageVariation(request, image);
    }

    @PostMapping("/v1/moderations")
    public ModerationResult createModeration(@RequestBody ModerationRequest request) {
        return openAiService.createModeration(request);
    }

//    @Deprecated
//    @GetMapping("v1/engines")
//    OpenAiResponse<Engine> getEngines();

//    @Deprecated
//    @GetMapping("/v1/engines/{engine_id}")
//    Engine getEngine(@PathVariable("engine_id") String var1);
}