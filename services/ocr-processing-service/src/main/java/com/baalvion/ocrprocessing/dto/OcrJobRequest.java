package com.baalvion.ocrprocessing.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OcrJobRequest {

	@NotNull(message = "Document ID is required")
	private UUID documentId;

	@NotBlank(message = "Submitted by is required")
	private String submittedBy;

	public OcrJobRequest() {
	}

	public OcrJobRequest(UUID documentId, String submittedBy) {
		this.documentId = documentId;
		this.submittedBy = submittedBy;
	}

	public UUID getDocumentId() {
		return documentId;
	}

	public void setDocumentId(UUID documentId) {
		this.documentId = documentId;
	}

	public String getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}
}