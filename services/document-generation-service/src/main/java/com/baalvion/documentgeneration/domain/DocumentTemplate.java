package com.baalvion.documentgeneration.domain;

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
@Table(name = "document_templates")
public class DocumentTemplate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6837797457637213376L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "document_template_id", nullable = false)
	private UUID documentTemplateId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String type;

	@Column(name = "template_content", columnDefinition = "TEXT")
	private String templateContent;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TemplateStatus status;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public DocumentTemplate() {
	}

	public DocumentTemplate(Long id, UUID documentTemplateId, String name, String type, String templateContent,
			TemplateStatus status, String createdBy, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.documentTemplateId = documentTemplateId;
		this.name = name;
		this.type = type;
		this.templateContent = templateContent;
		this.status = status;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private Long id;
		private UUID documentTemplateId;
		private String name;
		private String type;
		private String templateContent;
		private TemplateStatus status;
		private String createdBy;
		private LocalDateTime createdAt;
		private LocalDateTime updatedAt;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder documentTemplateId(UUID documentTemplateId) {
			this.documentTemplateId = documentTemplateId;
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

		public Builder templateContent(String templateContent) {
			this.templateContent = templateContent;
			return this;
		}

		public Builder status(TemplateStatus status) {
			this.status = status;
			return this;
		}

		public Builder createdBy(String createdBy) {
			this.createdBy = createdBy;
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

		public DocumentTemplate build() {
			return new DocumentTemplate(id, documentTemplateId, name, type, templateContent, status, createdBy,
					createdAt, updatedAt);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UUID getDocumentTemplateId() {
		return documentTemplateId;
	}

	public void setDocumentTemplateId(UUID documentTemplateId) {
		this.documentTemplateId = documentTemplateId;
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

	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}

	public TemplateStatus getStatus() {
		return status;
	}

	public void setStatus(TemplateStatus status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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

	// ✅ LIFECYCLE
	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();
		if (status == null)
			status = TemplateStatus.ACTIVE;
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}
}