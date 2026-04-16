package com.baalvion.documentversioning.controller;

import java.util.List;
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

import com.baalvion.documentversioning.dto.DocumentVersionRequest;
import com.baalvion.documentversioning.dto.DocumentVersionResponse;
import com.baalvion.documentversioning.exception.ApiResponse;
import com.baalvion.documentversioning.service.DocumentVersionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/versions")
public class DocumentVersionController {

	private static final Logger log = LoggerFactory.getLogger(DocumentVersionController.class);

	private final DocumentVersionService versionService;

	public DocumentVersionController(DocumentVersionService versionService) {
		this.versionService = versionService;
	}

	@PostMapping
	public ResponseEntity<ApiResponse<DocumentVersionResponse>> createVersion(
			@Valid @RequestBody DocumentVersionRequest request) {
		log.info("POST /api/v1/versions");
		DocumentVersionResponse response = versionService.createVersion(request);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponse.success("Version created successfully", response));
	}

	@GetMapping("/{documentId}")
	public ResponseEntity<ApiResponse<List<DocumentVersionResponse>>> getAllVersions(@PathVariable UUID documentId) {
		log.info("GET /api/v1/versions/{}", documentId);
		List<DocumentVersionResponse> response = versionService.getAllVersions(documentId);
		return ResponseEntity.ok(ApiResponse.success("Versions fetched successfully", response));
	}

	@GetMapping("/{documentId}/latest")
	public ResponseEntity<ApiResponse<DocumentVersionResponse>> getLatestVersion(@PathVariable UUID documentId) {
		log.info("GET /api/v1/versions/{}/latest", documentId);
		DocumentVersionResponse response = versionService.getLatestVersion(documentId);
		return ResponseEntity.ok(ApiResponse.success("Latest version fetched successfully", response));
	}

	@GetMapping("/detail/{versionId}")
	public ResponseEntity<ApiResponse<DocumentVersionResponse>> getVersion(@PathVariable UUID versionId) {
		log.info("GET /api/v1/versions/detail/{}", versionId);
		DocumentVersionResponse response = versionService.getVersion(versionId);
		return ResponseEntity.ok(ApiResponse.success("Version fetched successfully", response));
	}
}