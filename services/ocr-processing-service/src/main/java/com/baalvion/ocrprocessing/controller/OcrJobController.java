package com.baalvion.ocrprocessing.controller;

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

import com.baalvion.ocrprocessing.dto.OcrJobRequest;
import com.baalvion.ocrprocessing.dto.OcrJobResponse;
import com.baalvion.ocrprocessing.exception.ApiResponse;
import com.baalvion.ocrprocessing.service.OcrjobService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/ocr")
public class OcrJobController {

	private static final Logger log = LoggerFactory.getLogger(OcrJobController.class);

	private final OcrjobService ocrJobService;

	public OcrJobController(OcrjobService ocrJobService) {
		this.ocrJobService = ocrJobService;
	}

	@PostMapping("/process")
	public ResponseEntity<ApiResponse<OcrJobResponse>> submitOcrJob(@Valid @RequestBody OcrJobRequest request) {
		log.info("POST /api/v1/ocr/process");
		OcrJobResponse response = ocrJobService.submitOcrJob(request);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponse.success("OCR job submitted successfully", response));
	}

	@GetMapping("/status/{jobId}")
	public ResponseEntity<ApiResponse<OcrJobResponse>> getJobStatus(@PathVariable UUID jobId) {
		log.info("GET /api/v1/ocr/status/{}", jobId);
		OcrJobResponse response = ocrJobService.getJobStatus(jobId);
		return ResponseEntity.ok(ApiResponse.success("OCR job status fetched successfully", response));
	}

	@PostMapping("/process/{jobId}")
	public ResponseEntity<ApiResponse<OcrJobResponse>> processJob(@PathVariable UUID jobId) {
		log.info("POST /api/v1/ocr/process/{}", jobId);
		OcrJobResponse response = ocrJobService.processJob(jobId);
		return ResponseEntity.ok(ApiResponse.success("OCR job processed successfully", response));
	}

	@GetMapping("/document/{documentId}")
	public ResponseEntity<ApiResponse<List<OcrJobResponse>>> getJobsByDocumentId(@PathVariable UUID documentId) {
		log.info("GET /api/v1/ocr/document/{}", documentId);
		List<OcrJobResponse> response = ocrJobService.getJobsByDocumentId(documentId);
		return ResponseEntity.ok(ApiResponse.success("OCR jobs fetched successfully", response));
	}
}