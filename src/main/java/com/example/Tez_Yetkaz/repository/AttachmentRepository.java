package com.example.Tez_Yetkaz.repository;

import com.example.Tez_Yetkaz.entity.fr.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
    Optional<Attachment> findByIdAndDeletedFalse(UUID attachmentId);
}
