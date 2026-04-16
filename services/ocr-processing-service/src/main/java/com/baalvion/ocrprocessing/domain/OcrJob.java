package com.baalvion.ocrprocessing.domain;

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
@Table(name = "ocr_jobs")
public class OcrJob implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7203348484936394922L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ocrjob_id", nullable = false)
	private UUID ocrjobId;

	@Column(name = "document_id", nullable = false)
	private UUID documentId;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private OcrStatus status;

	@Column(name = "result", columnDefinition = "TEXT")
	private String result;

	@Column(name = "error_message")
	private String errorMessage;

	@Column(name = "submitted_by", nullable = false)
	private String submittedBy;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "completed_at")
	private LocalDateTime completedAt;

	public OcrJob() {
	}

	public OcrJob(UUID ocrjobId, UUID documentId, OcrStatus status, String result, String errorMessage,
			String submittedBy, LocalDateTime createdAt, LocalDateTime completedAt) {
		this.ocrjobId = ocrjobId;
		this.documentId = documentId;
		this.status = status;
		this.result = result;
		this.errorMessage = errorMessage;
		this.submittedBy = submittedBy;
		this.createdAt = createdAt;
		this.completedAt = completedAt;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private UUID ocrjobId;
		private UUID documentId;
		private OcrStatus status;
		private String result;
		private String errorMessage;
		private String submittedBy;
		private LocalDateTime createdAt;
		private LocalDateTime completedAt;

		public Builder ocrjobId(UUID ocrjobId) {
			this.ocrjobId = ocrjobId;
			return this;
		}

		public Builder documentId(UUID documentId) {
			this.documentId = documentId;
			return this;
		}

		public Builder status(OcrStatus status) {
			this.status = status;
			return this;
		}

		public Builder result(String result) {
			this.result = result;
			return this;
		}

		public Builder errorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
			return this;
		}

		public Builder submittedBy(String submittedBy) {
			this.submittedBy = submittedBy;
			return this;
		}

		public Builder createdAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public Builder completedAt(LocalDateTime completedAt) {
			this.completedAt = completedAt;
			return this;
		}

		public OcrJob build() {
			return new OcrJob(ocrjobId, documentId, status, result, errorMessage, submittedBy, createdAt, completedAt);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UUID getOcrjobId() {
		return ocrjobId;
	}

	public void setOcrjobId(UUID ocrjobId) {
		this.ocrjobId = ocrjobId;
	}

	public UUID getDocumentId() {
		return documentId;
	}

	public void setDocumentId(UUID documentId) {
		this.documentId = documentId;
	}

	public OcrStatus getStatus() {
		return status;
	}

	public void setStatus(OcrStatus status) {
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getCompletedAt() {
		return completedAt;
	}

	public void setCompletedAt(LocalDateTime completedAt) {
		this.completedAt = completedAt;
	}

	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
		if (status == null)
			status = OcrStatus.PENDING;
	}
}