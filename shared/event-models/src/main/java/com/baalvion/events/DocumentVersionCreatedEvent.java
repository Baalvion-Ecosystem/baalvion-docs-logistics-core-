package com.baalvion.events;

import java.time.LocalDateTime;
import java.util.UUID;

public class DocumentVersionCreatedEvent {

	private UUID versionId;
	private UUID documentId;
	private Integer versionNumber;
	private String modifiedBy;
	private LocalDateTime createdAt;

	public DocumentVersionCreatedEvent() {
	}

	public DocumentVersionCreatedEvent(UUID versionId, UUID documentId, Integer versionNumber, String modifiedBy,
			LocalDateTime createdAt) {
		this.versionId = versionId;
		this.documentId = documentId;
		this.versionNumber = versionNumber;
		this.modifiedBy = modifiedBy;
		this.createdAt = createdAt;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private UUID versionId;
		private UUID documentId;
		private Integer versionNumber;
		private String modifiedBy;
		private LocalDateTime createdAt;

		public Builder versionId(UUID versionId) {
			this.versionId = versionId;
			return this;
		}

		public Builder documentId(UUID documentId) {
			this.documentId = documentId;
			return this;
		}

		public Builder versionNumber(Integer versionNumber) {
			this.versionNumber = versionNumber;
			return this;
		}

		public Builder modifiedBy(String modifiedBy) {
			this.modifiedBy = modifiedBy;
			return this;
		}

		public Builder createdAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public DocumentVersionCreatedEvent build() {
			return new DocumentVersionCreatedEvent(versionId, documentId, versionNumber, modifiedBy, createdAt);
		}
	}

	public UUID getVersionId() {
		return versionId;
	}

	public void setVersionId(UUID versionId) {
		this.versionId = versionId;
	}

	public UUID getDocumentId() {
		return documentId;
	}

	public void setDocumentId(UUID documentId) {
		this.documentId = documentId;
	}

	public Integer getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(Integer versionNumber) {
		this.versionNumber = versionNumber;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}