package com.example.Tez_Yetkaz.service;

import com.example.Tez_Yetkaz.entity.fr.Attachment;
import com.example.Tez_Yetkaz.entity.fr.AttachmentContent;
import com.example.Tez_Yetkaz.exception.NotFoundException;
import com.example.Tez_Yetkaz.repository.AttachmentContentRepository;
import com.example.Tez_Yetkaz.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class FileService {


    private final AttachmentRepository fileRepository;
    private final AttachmentContentRepository attachmentContentRepository;


    public boolean isValidFile(MultipartFile multipartFile) {
        String contentType = multipartFile.getContentType();

        assert contentType != null;
        return contentType.equals("image/jpeg") || contentType.equals("image/png");
//                || contentType.equals("text/plain") || contentType.equals("application/pdf")
//                || contentType.equals("image/gif") || contentType.equals("application/zip")
//                || contentType.equals("application/vnd.malformations-office document.spreadsheet.sheet")
//                || contentType.equals("video/mp4")||contentType.equals("video/ogg")
//                || contentType.equals("audio/mpeg") || contentType.equals("audio/ogg")
//                || contentType.equals("audio/mp3") || contentType.equals("audio/m4a");
    }

    @Transactional
    public Attachment saved(MultipartFile multipartFile) throws IOException {
        AttachmentContent attachmentContent = new AttachmentContent();

        if (!isValidFile(multipartFile)) {
            throw new IllegalArgumentException("file is not compatible");
        }
        //byte[] bytes = ImageUtils.comparesImage(multipartFile.getBytes());

        Attachment attachment = new Attachment();

        attachment.setName(multipartFile.getName());
        attachment.setFileOriginalName(multipartFile.getOriginalFilename());
        attachment.setContentType(multipartFile.getContentType());
        attachment.setSize(multipartFile.getSize());

        Attachment saved = fileRepository.save(attachment);
        attachmentContent.setMainContent(multipartFile.getBytes());
        attachmentContent.setAttachment(saved);
        attachmentContentRepository.save(attachmentContent);

        return saved;

    }


//    public ResponseData<Attachment> downloadFile(UUID fileId) {
//        Optional<Attachment> optionalAttachment = fileRepository.findById(fileId);
//        if (optionalAttachment.isEmpty()) {
//            throw new NotFoundException("file not found ");
//        }
//        return ResponseData.successResponse(optionalAttachment.get());
//    }

    public boolean deleteFile(UUID fileId) {
        Optional<Attachment> optionalAttachment = fileRepository.findById(fileId);
        if (optionalAttachment.isEmpty()) {
            throw new NotFoundException("file not found");
        }
        Attachment attachment = optionalAttachment.get();
        fileRepository.deleteById(attachment.getId());
        Optional<Attachment> optionalAttachment1 = fileRepository.findById(fileId);
        return optionalAttachment1.isEmpty();
    }

}
