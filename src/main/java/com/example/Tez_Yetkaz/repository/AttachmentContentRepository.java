package com.example.Tez_Yetkaz.repository;

import com.example.Tez_Yetkaz.entity.fr.AttachmentContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, UUID> {
}
