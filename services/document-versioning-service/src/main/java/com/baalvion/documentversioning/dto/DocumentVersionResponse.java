package com.baalvion.documentversioning.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.baalvion.documentversioning.domain.VersionStatus;

public class DocumentVersionResponse {

	private UUID documentVersionsId;
	private UUID documentId;
	private Integer versionNumber;
	private String storagePath;
	private String modifiedBy;
	private String changeSummary;
	private VersionStatus status;
	private LocalDateTime createdAt;

	public DocumentVersionResponse() {
	}

	public DocumentVersionResponse(UUID documentVersionsId, UUID documentId, Integer versionNumber, String storagePath,
			String modifiedBy, String changeSummary, VersionStatus status, LocalDateTime createdAt) {
		this.documentVersionsId = documentVersionsId;
		this.documentId = documentId;
		this.versionNumber = versionNumber;
		this.storagePath = storagePath;
		this.modifiedBy = modifiedBy;
		this.changeSummary = changeSummary;
		this.status = status;
		this.createdAt = createdAt;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private UUID documentVersionsId;
		private UUID documentId;
		private Integer versionNumber;
		private String storagePath;
		private String modifiedBy;
		private String changeSummary;
		private VersionStatus status;
		private LocalDateTime createdAt;

		public Builder documentVersionsId(UUID documentVersionsId) {
			this.documentVersionsId = documentVersionsId;
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

		public Builder storagePath(String storagePath) {
			this.storagePath = storagePath;
			return this;
		}

		public Builder modifiedBy(String modifiedBy) {
			this.modifiedBy = modifiedBy;
			return this;
		}

		public Builder changeSummary(String changeSummary) {
			this.changeSummary = changeSummary;
			return this;
		}

		public Builder status(VersionStatus status) {
			this.status = status;
			return this;
		}

		public Builder createdAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public DocumentVersionResponse build() {
			return new DocumentVersionResponse(documentVersionsId, documentId, versionNumber, storagePath, modifiedBy,
					changeSummary, status, createdAt);
		}
	}

	public UUID getDocumentVersionsId() {
		return documentVersionsId;
	}

	public void setDocumentVersionsId(UUID documentVersionsId) {
		this.documentVersionsId = documentVersionsId;
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

	public VersionStatus getStatus() {
		return status;
	}

	public void setStatus(VersionStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}