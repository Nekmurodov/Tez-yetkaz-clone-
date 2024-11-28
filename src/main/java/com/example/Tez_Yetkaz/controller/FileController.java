package com.example.Tez_Yetkaz.controller;
import com.example.Tez_Yetkaz.response.ResponseData;
import com.example.Tez_Yetkaz.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@Validated
@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {

//    private final FileService fileService;

//    @PostMapping(value = "/file-upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseData<?> attachmentContentResponseEntity(@Validated @RequestParam("file") MultipartFile multipartFile)
//            throws IOException {
//        return this.fileService.saved(multipartFile);
//    }

//    @GetMapping("/file-download/{fileId}")
//    public ResponseData<?> downloadFile(@PathVariable UUID fileId) {
//        return this.fileService.downloadFile(fileId);
//    }

//    @DeleteMapping("/delete-images{fileId}")
//    public ResponseData<?> deleteFile(@PathVariable UUID fileId) {
//        return this.fileService.deleteFile(fileId);
//    }

}
