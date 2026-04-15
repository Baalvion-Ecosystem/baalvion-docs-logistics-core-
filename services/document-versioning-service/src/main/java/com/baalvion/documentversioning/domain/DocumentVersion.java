package com.baalvion.documentversioning.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "document_versions")
public class DocumentVersion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8685295084633087300L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "document_versions_id", nullable = false)
	private UUID documentVersionsId;

	@Column(name = "document_id", nullable = false)
	private UUID documentId;

	@Column(name = "version_number", nullable = false)
	private Integer versionNumber;

	@Column(name = "storage_path", nullable = false)
	private String storagePath;

	@Column(name = "modified_by", nullable = false)
	private String modifiedBy;

	@Column(name = "change_summary")
	private String changeSummary;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private VersionStatus status;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	public DocumentVersion() {
	}

	public DocumentVersion(Long id, UUID documentVersionsId, UUID documentId, Integer versionNumber, String storagePath,
			String modifiedBy, String changeSummary, VersionStatus status, LocalDateTime createdAt) {
		this.id = id;
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
		private Long id;
		private UUID documentVersionsId;
		private UUID documentId;
		private Integer versionNumber;
		private String storagePath;
		private String modifiedBy;
		private String changeSummary;
		private VersionStatus status;
		private LocalDateTime createdAt;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

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

		public DocumentVersion build() {
			return new DocumentVersion(id, documentVersionsId, documentId, versionNumber, storagePath, modifiedBy,
					changeSummary, status, createdAt);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	// ✅ LIFECYCLE
	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
		if (status == null)
			status = VersionStatus.ACTIVE;
	}
}