package com.baalvion.documentvault.repository;

import com.baalvion.documentvault.domain.Document;
import com.baalvion.documentvault.domain.DocumentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<Document, UUID> {

    List<Document> findByUploadedBy(String uploadedBy);

    List<Document> findByStatus(DocumentStatus status);

    List<Document> findByType(String type);
}