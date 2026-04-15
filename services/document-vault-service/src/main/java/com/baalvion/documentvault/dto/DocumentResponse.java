package com.baalvion.documentvault.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.baalvion.documentvault.domain.DocumentStatus;

public class DocumentResponse {

	private UUID documentId;
	private String name;
	private String type;
	private String storagePath;
	private String uploadedBy;
	private DocumentStatus status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public DocumentResponse() {
	}

	public DocumentResponse(UUID documentId, String name, String type, String storagePath, String uploadedBy,
			DocumentStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.documentId = documentId;
		this.name = name;
		this.type = type;
		this.storagePath = storagePath;
		this.uploadedBy = uploadedBy;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private UUID documentId;
		private String name;
		private String type;
		private String storagePath;
		private String uploadedBy;
		private DocumentStatus status;
		private LocalDateTime createdAt;
		private LocalDateTime updatedAt;

		public Builder documentId(UUID documentId) {
			this.documentId = documentId;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder type(String type) {
			this.type = type;
			return this;
		}

		public Builder storagePath(String storagePath) {
			this.storagePath = storagePath;
			return this;
		}

		public Builder uploadedBy(String uploadedBy) {
			this.uploadedBy = uploadedBy;
			return this;
		}

		public Builder status(DocumentStatus status) {
			this.status = status;
			return this;
		}

		public Builder createdAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public Builder updatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
			return this;
		}

		public DocumentResponse build() {
			return new DocumentResponse(documentId, name, type, storagePath, uploadedBy, status, createdAt, updatedAt);
		}
	}

	public UUID getDocumentId() {
		return documentId;
	}

	public void setDocumentId(UUID documentId) {
		this.documentId = documentId;
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

	public DocumentStatus getStatus() {
		return status;
	}

	public void setStatus(DocumentStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}