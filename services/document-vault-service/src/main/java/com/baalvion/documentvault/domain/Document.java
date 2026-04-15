package com.baalvion.documentvault.domain;

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
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "documents")
public class Document implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2100758101947592121L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "document_id", nullable = false)
	private UUID documentId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String type;

	@Column(name = "storage_path", nullable = false)
	private String storagePath;

	@Column(name = "uploaded_by", nullable = false)
	private String uploadedBy;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private DocumentStatus status;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public Document() {
	}

	public Document(Long id, UUID documentId, String name, String type, String storagePath, String uploadedBy,
			DocumentStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
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
		private Long id;
		private UUID documentId;
		private String name;
		private String type;
		private String storagePath;
		private String uploadedBy;
		private DocumentStatus status;
		private LocalDateTime createdAt;
		private LocalDateTime updatedAt;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

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

		public Document build() {
			return new Document(id, documentId, name, type, storagePath, uploadedBy, status, createdAt, updatedAt);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();
		if (status == null)
			status = DocumentStatus.ACTIVE;
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}
}