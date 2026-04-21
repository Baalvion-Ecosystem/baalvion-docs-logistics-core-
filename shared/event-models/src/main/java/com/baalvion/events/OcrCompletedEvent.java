package com.baalvion.events;

import java.time.LocalDateTime;
import java.util.UUID;

public class OcrCompletedEvent {

	private UUID jobId;
	private UUID documentId;
	private String result;
	private String status;
	private LocalDateTime completedAt;

	public OcrCompletedEvent() {
	}

	public OcrCompletedEvent(UUID jobId, UUID documentId, String result, String status, LocalDateTime completedAt) {
		this.jobId = jobId;
		this.documentId = documentId;
		this.result = result;
		this.status = status;
		this.completedAt = completedAt;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private UUID jobId;
		private UUID documentId;
		private String result;
		private String status;
		private LocalDateTime completedAt;

		public Builder jobId(UUID jobId) {
			this.jobId = jobId;
			return this;
		}

		public Builder documentId(UUID documentId) {
			this.documentId = documentId;
			return this;
		}

		public Builder result(String result) {
			this.result = result;
			return this;
		}

		public Builder status(String status) {
			this.status = status;
			return this;
		}

		public Builder completedAt(LocalDateTime completedAt) {
			this.completedAt = completedAt;
			return this;
		}

		public OcrCompletedEvent build() {
			return new OcrCompletedEvent(jobId, documentId, result, status, completedAt);
		}
	}

	public UUID getJobId() {
		return jobId;
	}

	public void setJobId(UUID jobId) {
		this.jobId = jobId;
	}

	public UUID getDocumentId() {
		return documentId;
	}

	public void setDocumentId(UUID documentId) {
		this.documentId = documentId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCompletedAt() {
		return completedAt;
	}

	public void setCompletedAt(LocalDateTime completedAt) {
		this.completedAt = completedAt;
	}
}