package com.baalvion.documentversioning.controller;

import com.baalvion.documentversioning.dto.DocumentVersionRequest;
import com.baalvion.documentversioning.dto.DocumentVersionResponse;
import com.baalvion.documentversioning.service.DocumentVersionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/versions")
public class DocumentVersionController {

    private static final Logger log = LoggerFactory.getLogger(DocumentVersionController.class);

    private final DocumentVersionService versionService;

    public DocumentVersionController(DocumentVersionService versionService) {
        this.versionService = versionService;
    }

    @PostMapping
    public ResponseEntity<DocumentVersionResponse> createVersion(
            @Valid @RequestBody DocumentVersionRequest request) {
        log.info("POST /api/v1/versions");
        DocumentVersionResponse response = versionService.createVersion(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<List<DocumentVersionResponse>> getAllVersions(
            @PathVariable UUID documentId) {
        log.info("GET /api/v1/versions/{}", documentId);
        List<DocumentVersionResponse> response = versionService.getAllVersions(documentId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{documentId}/latest")
    public ResponseEntity<DocumentVersionResponse> getLatestVersion(
            @PathVariable UUID documentId) {
        log.info("GET /api/v1/versions/{}/latest", documentId);
        DocumentVersionResponse response = versionService.getLatestVersion(documentId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/detail/{versionId}")
    public ResponseEntity<DocumentVersionResponse> getVersion(
            @PathVariable UUID versionId) {
        log.info("GET /api/v1/versions/detail/{}", versionId);
        DocumentVersionResponse response = versionService.getVersion(versionId);
        return ResponseEntity.ok(response);
    }
}