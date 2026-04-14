package com.baalvion.documentvault.dto;

import jakarta.validation.constraints.NotBlank;

public class DocumentRequest {

	@NotBlank(message = "Document name is required")
	private String name;

	@NotBlank(message = "Document type is required")
	private String type;

	@NotBlank(message = "Storage path is required")
	private String storagePath;

	@NotBlank(message = "Uploaded by is required")
	private String uploadedBy;

	public DocumentRequest() {
	}

	public DocumentRequest(String name, String type, String storagePath, String uploadedBy) {
		this.name = name;
		this.type = type;
		this.storagePath = storagePath;
		this.uploadedBy = uploadedBy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStoragePath() {
		return storagePath;
	}

	public void setStoragePath(String storagePath) {
		this.storagePath = storagePath;
	}

	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}
}