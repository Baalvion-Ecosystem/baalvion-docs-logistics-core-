package com.baalvion.documentversioning.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DocumentVersionRequest {

	@NotNull(message = "Document ID is required")
	private UUID documentId;

	@NotBlank(message = "Storage path is required")
	private String storagePath;

	@NotBlank(message = "Modified by is required")
	private String modifiedBy;

	private String changeSummary;

	public DocumentVersionRequest() {
	}

	public DocumentVersionRequest(UUID documentId, String storagePath, String modifiedBy, String changeSummary) {
		this.documentId = documentId;
		this.storagePath = storagePath;
		this.modifiedBy = modifiedBy;
		this.changeSummary = changeSummary;
	}

	public UUID getDocumentId() {
		return documentId;
	}

	public void setDocumentId(UUID documentId) {
		this.documentId = documentId;
	}

	public String getStoragePath() {
		return storagePath;
	}

	public void setStoragePath(String storagePath) {
		this.storagePath = storagePath;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getChangeSummary() {
		return changeSummary;
	}

	public void setChangeSummary(String changeSummary) {
		this.changeSummary = changeSummary;
	}
}