package com.baalvion.documentvault.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baalvion.documentvault.domain.Document;
import com.baalvion.documentvault.domain.DocumentStatus;
import java.util.UUID;


@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
	
	Optional<Document> findByDocumentId(UUID documentId);

	List<Document> findByUploadedBy(String uploadedBy);

	List<Document> findByStatus(DocumentStatus status);

	List<Document> findByType(String type);
}