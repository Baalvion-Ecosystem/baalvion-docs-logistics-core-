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
public class DocumentServiceImpl implements DocumentService {

	private static final Logger log = LoggerFactory.getLogger(DocumentServiceImpl.class);

	private final DocumentRepository documentRepository;

	public DocumentServiceImpl(DocumentRepository documentRepository) {
		super();
		this.documentRepository = documentRepository;
	}

	@Override
	public DocumentResponse uploadDocument(DocumentRequest request) {
		log.info("Uploading document: {}", request.getName());

		Document document = Document.builder().name(request.getName()).type(request.getType())
				.storagePath(request.getStoragePath()).uploadedBy(request.getUploadedBy()).status(DocumentStatus.ACTIVE)
				.build();

		Document saved = documentRepository.save(document);
		log.info("Document saved with documentId: {}", saved.getDocumentId());

		return mapToResponse(saved);
	}

	@Override
	public DocumentResponse getDocument(UUID documentId) {
		log.info("Fetching document with documentId: {}", documentId);

		Document document = documentRepository.findByDocumentId(documentId)
				.orElseThrow(() -> new RuntimeException("Document not found with documentId: " + documentId));

		return mapToResponse(document);
	}

	@Override
	public List<DocumentResponse> getAllDocuments() {
		log.info("Fetching all documents");

		return documentRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	@Override
	public void deleteDocument(UUID documentId) {
		log.info("Deleting document with documentId: {}", documentId);

		Document document = documentRepository.findByDocumentId(documentId)
				.orElseThrow(() -> new RuntimeException("Document not found with documentId: " + documentId));

		document.setStatus(DocumentStatus.DELETED);
		documentRepository.save(document);
		log.info("Document deleted with documentId: {}", documentId);
	}

	private DocumentResponse mapToResponse(Document document) {
		return DocumentResponse.builder().documentId(document.getDocumentId()).name(document.getName())
				.type(document.getType()).storagePath(document.getStoragePath()).uploadedBy(document.getUploadedBy())
				.status(document.getStatus()).createdAt(document.getCreatedAt()).updatedAt(document.getUpdatedAt())
				.build();
	}
}