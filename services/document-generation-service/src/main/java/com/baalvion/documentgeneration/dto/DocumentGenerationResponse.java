package com.baalvion.documentgeneration.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class DocumentGenerationResponse {

	private UUID documentTemplateId;
	private String generatedBy;
	private String generatedContent;
	private String status;
	private LocalDateTime createdAt;

	public DocumentGenerationResponse() {
	}

	public DocumentGenerationResponse(UUID documentTemplateId, String generatedBy, String generatedContent,
			String status, LocalDateTime createdAt) {
		this.documentTemplateId = documentTemplateId;
		this.generatedBy = generatedBy;
		this.generatedContent = generatedContent;
		this.status = status;
		this.createdAt = createdAt;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private UUID documentTemplateId;
		private String generatedBy;
		private String generatedContent;
		private String status;
		private LocalDateTime createdAt;

		public Builder documentTemplateId(UUID documentTemplateId) {
			this.documentTemplateId = documentTemplateId;
			return this;
		}

		public Builder generatedBy(String generatedBy) {
			this.generatedBy = generatedBy;
			return this;
		}

		public Builder generatedContent(String generatedContent) {
			this.generatedContent = generatedContent;
			return this;
		}

		public Builder status(String status) {
			this.status = status;
			return this;
		}

		public Builder createdAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public DocumentGenerationResponse build() {
			return new DocumentGenerationResponse(documentTemplateId, generatedBy, generatedContent, status, createdAt);
		}
	}

	public UUID getDocumentTemplateId() {
		return documentTemplateId;
	}

	public void setDocumentTemplateId(UUID documentTemplateId) {
		this.documentTemplateId = documentTemplateId;
	}

	public String getGeneratedBy() {
		return generatedBy;
	}

	public void setGeneratedBy(String generatedBy) {
		this.generatedBy = generatedBy;
	}

	public String getGeneratedContent() {
		return generatedContent;
	}

	public void setGeneratedContent(String generatedContent) {
		this.generatedContent = generatedContent;
		return;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}