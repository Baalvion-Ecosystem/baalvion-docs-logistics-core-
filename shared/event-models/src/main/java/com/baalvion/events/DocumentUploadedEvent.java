package com.baalvion.events;

import java.time.LocalDateTime;
import java.util.UUID;

public class DocumentUploadedEvent {

	private UUID documentId;
	private String documentName;
	private String documentType;
	private String uploadedBy;
	private LocalDateTime uploadedAt;

	public DocumentUploadedEvent() {
	}

	public DocumentUploadedEvent(UUID documentId, String documentName, String documentType, String uploadedBy,
			LocalDateTime uploadedAt) {
		this.documentId = documentId;
		this.documentName = documentName;
		this.documentType = documentType;
		this.uploadedBy = uploadedBy;
		this.uploadedAt = uploadedAt;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private UUID documentId;
		private String documentName;
		private String documentType;
		private String uploadedBy;
		private LocalDateTime uploadedAt;

		public Builder documentId(UUID documentId) {
			this.documentId = documentId;
			return this;
		}

		public Builder documentName(String documentName) {
			this.documentName = documentName;
			return this;
		}

		public Builder documentType(String documentType) {
			this.documentType = documentType;
			return this;
		}

		public Builder uploadedBy(String uploadedBy) {
			this.uploadedBy = uploadedBy;
			return this;
		}

		public Builder uploadedAt(LocalDateTime uploadedAt) {
			this.uploadedAt = uploadedAt;
			return this;
		}

		public DocumentUploadedEvent build() {
			return new DocumentUploadedEvent(documentId, documentName, documentType, uploadedBy, uploadedAt);
		}
	}

	public UUID getDocumentId() {
		return documentId;
	}

	public void setDocumentId(UUID documentId) {
		this.documentId = documentId;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public LocalDateTime getUploadedAt() {
		return uploadedAt;
	}

	public void setUploadedAt(LocalDateTime uploadedAt) {
		this.uploadedAt = uploadedAt;
	}
}