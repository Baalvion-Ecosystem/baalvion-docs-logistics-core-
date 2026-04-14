package com.baalvion.documentvault.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.baalvion.documentvault.domain.Document;
import com.baalvion.documentvault.domain.DocumentStatus;
import com.baalvion.documentvault.dto.DocumentRequest;
import com.baalvion.documentvault.dto.DocumentResponse;
import com.baalvion.documentvault.repository.DocumentRepository;

@Service("documentService")
public class DocumentService {

	Logger logger = LoggerFactory.getLogger(DocumentService.class);

	private final DocumentRepository documentRepository;

	public DocumentService(DocumentRepository documentRepository) {
		super();
		this.documentRepository = documentRepository;
	}

	public DocumentResponse uploadDocument(DocumentRequest request) {
		logger.info("Uploading document: {}", request.getName());

		Document document = Document.builder().name(request.getName()).type(request.getType())
				.storagePath(request.getStoragePath()).uploadedBy(request.getUploadedBy()).status(DocumentStatus.ACTIVE)
				.build();

		Document saved = documentRepository.save(document);
		logger.info("Document saved with id: {}", saved.getId());

		return mapToResponse(saved);
	}

	public DocumentResponse getDocument(UUID id) {
		logger.info("Fetching document with id: {}", id);

		Document document = documentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Document not found with id: " + id));

		return mapToResponse(document);
	}

	public List<DocumentResponse> getAllDocuments() {
		logger.info("Fetching all documents");

		return documentRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	public void deleteDocument(UUID id) {
		logger.info("Deleting document with id: {}", id);

		Document document = documentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Document not found with id: " + id));

		document.setStatus(DocumentStatus.DELETED);
		documentRepository.save(document);
		logger.info("Document deleted with id: {}", id);
	}

	private DocumentResponse mapToResponse(Document document) {
		return DocumentResponse.builder().id(document.getId()).name(document.getName()).type(document.getType())
				.storagePath(document.getStoragePath()).uploadedBy(document.getUploadedBy())
				.status(document.getStatus()).createdAt(document.getCreatedAt()).updatedAt(document.getUpdatedAt())
				.build();
	}
}