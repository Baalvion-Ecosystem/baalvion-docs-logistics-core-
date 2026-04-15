package com.baalvion.documentvault.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baalvion.documentvault.dto.DocumentRequest;
import com.baalvion.documentvault.dto.DocumentResponse;
import com.baalvion.documentvault.service.DocumentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/documents")
public class DocumentController {

	private static final Logger log = LoggerFactory.getLogger(DocumentController.class);

	private final DocumentService documentService;

	public DocumentController(DocumentService documentService) {
		super();
		this.documentService = documentService;
	}

	@PostMapping
	public ResponseEntity<DocumentResponse> uploadDocument(@Valid @RequestBody DocumentRequest request) {
		log.info("POST /api/v1/documents");
		DocumentResponse response = documentService.uploadDocument(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DocumentResponse> getDocument(@PathVariable UUID id) {
		log.info("GET /api/v1/documents/{}", id);
		DocumentResponse response = documentService.getDocument(id);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<DocumentResponse>> getAllDocuments() {
		log.info("GET /api/v1/documents");
		List<DocumentResponse> response = documentService.getAllDocuments();
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDocument(@PathVariable UUID id) {
		log.info("DELETE /api/v1/documents/{}", id);
		documentService.deleteDocument(id);
		return ResponseEntity.noContent().build();
	}
}