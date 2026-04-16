package com.baalvion.documentgeneration.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baalvion.documentgeneration.dto.DocumentGenerationRequest;
import com.baalvion.documentgeneration.dto.DocumentGenerationResponse;
import com.baalvion.documentgeneration.exception.ApiResponse;
import com.baalvion.documentgeneration.service.DocumentGenerationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/documents/generate")
public class DocumentGenerationController {

	private static final Logger log = LoggerFactory.getLogger(DocumentGenerationController.class);

	private final DocumentGenerationService documentGenerationService;

	public DocumentGenerationController(DocumentGenerationService documentGenerationService) {
		this.documentGenerationService = documentGenerationService;
	}

	@PostMapping
	public ResponseEntity<ApiResponse<DocumentGenerationResponse>> generateDocument(
			@Valid @RequestBody DocumentGenerationRequest request) {
		log.info("POST /api/v1/documents/generate");
		DocumentGenerationResponse response = documentGenerationService.generateDocument(request);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponse.success("Document generated successfully", response));
	}

	@GetMapping("/templates/{templateId}")
	public ResponseEntity<ApiResponse<DocumentGenerationResponse>> getTemplate(@PathVariable UUID templateId) {
		log.info("GET /api/v1/documents/generate/templates/{}", templateId);
		DocumentGenerationResponse response = documentGenerationService.getTemplate(templateId);
		return ResponseEntity.ok(ApiResponse.success("Template fetched successfully", response));
	}
}