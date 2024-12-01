package com.example.Tez_Yetkaz.controller;
import com.example.Tez_Yetkaz.entity.fr.Attachment;
import com.example.Tez_Yetkaz.entity.fr.AttachmentContent;
import com.example.Tez_Yetkaz.response.ResponseData;
import com.example.Tez_Yetkaz.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    private final FileService fileService;

    @PostMapping(value = "/file-upload",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<?> attachmentContentResponseEntity(@Validated @RequestParam("file") MultipartFile multipartFile)
            throws IOException {
        return this.fileService.savedFile(multipartFile);
    }

    @GetMapping("/file-download/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable UUID fileId) {
        ResponseData<Attachment> responseData = fileService.downloadFile(fileId);
        Attachment content = responseData.getData();
        AttachmentContent attachmentContent = content.getAttachmentContent();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(content.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + content.getFileOriginalName() + "\"")
                .body(new ByteArrayResource(attachmentContent.getMainContent()));
    }

    @GetMapping("/file-show/{fileId}")
    public ResponseEntity<ByteArrayResource> showFile(@PathVariable UUID fileId) {
        ResponseData<Attachment> responseData = fileService.downloadFile(fileId);
        Attachment content = responseData.getData();
        AttachmentContent attachmentContent = content.getAttachmentContent();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(content.getContentType())) // Fayl turi (image/jpeg, image/png, va hokazo)
                .body(new ByteArrayResource(attachmentContent.getMainContent())); // Asosiy kontentni jo'natadi
    }

    @DeleteMapping("/delete-images{fileId}")
    public ResponseData<?> deleteFile(@PathVariable UUID fileId) {
        return this.fileService.deletedFile(fileId);
    }

}
