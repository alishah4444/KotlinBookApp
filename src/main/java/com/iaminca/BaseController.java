package com.iaminca;

import com.theokanning.openai.DeleteResult;
import com.theokanning.openai.OpenAiResponse;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.edit.EditRequest;
import com.theokanning.openai.edit.EditResult;
import com.theokanning.openai.embedding.EmbeddingRequest;
import com.theokanning.openai.embedding.EmbeddingResult;
import com.theokanning.openai.file.File;
import com.theokanning.openai.finetune.FineTuneEvent;
import com.theokanning.openai.finetune.FineTuneRequest;
import com.theokanning.openai.finetune.FineTuneResult;
import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.image.ImageResult;
import com.theokanning.openai.model.Model;
import com.theokanning.openai.moderation.ModerationRequest;
import com.theokanning.openai.moderation.ModerationResult;
import com.theokanning.openai.service.OpenAiService;
import io.reactivex.Flowable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;

import static org.springframework.http.MediaType.ALL_VALUE;

@RestController
public class BaseController {

    private final OpenAiService openAiService;

    @Autowired
    public BaseController(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

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

    private <Req, Chunk, B> ResponseEntity<?> selectStream(
            Boolean stream,
            Function<? super Req, ? extends Flowable<Chunk>> ifStream,
            Function<? super Req, B> notStream,
            Req request) {
        if (Boolean.TRUE.equals(stream)) {
            return ResponseEntity.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(ifStream.apply(request));
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(notStream.apply(request));
    }

    @PostMapping(value = "/v1/completions", produces = ALL_VALUE)
    public ResponseEntity<?> createCompletion(@RequestBody CompletionRequest request) {
        return selectStream(request.getStream(), openAiService::streamCompletion, openAiService::createCompletion, request);
    }

    @PostMapping(value = "/v1/chat/completions", produces = ALL_VALUE)
    public ResponseEntity<?> createChatCompletion(@RequestBody ChatCompletionRequest request) {
        return selectStream(request.getStream(), openAiService::streamChatCompletion, openAiService::createChatCompletion, request);
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

    // TODO MultipartFile
//    @PostMapping("/v1/files")
//    public File uploadFile(
//            @RequestParam("purpose") String purpose,
//            @RequestParam("file") MultipartFile multipartFile)
//            throws IOException {
//        okhttp3.RequestBody purposeBody = okhttp3.RequestBody.create(okhttp3.MultipartBody.FORM, purpose);
//        okhttp3.RequestBody fileBody = okhttp3.RequestBody.create(MediaType.parse("text"), multipartFile.getBytes());
//        MultipartBody.Part body = MultipartBody.Part.createFormData("file", multipartFile.getOriginalFilename(), fileBody);
//
//        return openAiService.execute(openAiService.api.uploadFile(purposeBody, body));
//    }

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

    // TODO MultipartFile
//    @PostMapping("/v1/images/edits")
//    public ImageResult createImageEdit(@RequestBody RequestBody requestBody) {
//        return openAiService.createImageEdit(requestBody);
//    }

    // TODO MultipartFile
//    @PostMapping("/v1/images/variations")
//    public ImageResult createImageVariation(@RequestBody RequestBody requestBody){
//        return openAiService.createImageVariation(requestBody);
//    }

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
