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

	private static final Logger log = LoggerFactory.getLogger(DocumentService.class);

	private final DocumentRepository documentRepository;

	public DocumentService(DocumentRepository documentRepository) {
		super();
		this.documentRepository = documentRepository;
	}

	public DocumentResponse uploadDocument(DocumentRequest request) {
		log.info("Uploading document: {}", request.getName());

		Document document = Document.builder().name(request.getName()).type(request.getType())
				.storagePath(request.getStoragePath()).uploadedBy(request.getUploadedBy()).status(DocumentStatus.ACTIVE)
				.build();

		Document saved = documentRepository.save(document);
		log.info("Document saved with id: {}", saved.getId());

		return mapToResponse(saved);
	}

	public DocumentResponse getDocument(UUID documentId) {
		log.info("Fetching document with id: {}", documentId);

		Document document = documentRepository.findByDocumentId(documentId)
				.orElseThrow(() -> new RuntimeException("Document not found with id: " + documentId));

		return mapToResponse(document);
	}

	public List<DocumentResponse> getAllDocuments() {
		log.info("Fetching all documents");

		return documentRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	public void deleteDocument(UUID documentId) {
		log.info("Deleting document with id: {}", documentId);

		Document document = documentRepository.findByDocumentId(documentId)
				.orElseThrow(() -> new RuntimeException("Document not found with id: " + documentId));

		document.setStatus(DocumentStatus.DELETED);
		documentRepository.save(document);
		log.info("Document deleted with id: {}", documentId);
	}

	private DocumentResponse mapToResponse(Document document) {
		return DocumentResponse.builder().documentId(document.getDocumentId()).name(document.getName()).type(document.getType())
				.storagePath(document.getStoragePath()).uploadedBy(document.getUploadedBy())
				.status(document.getStatus()).createdAt(document.getCreatedAt()).updatedAt(document.getUpdatedAt())
				.build();
	}
}