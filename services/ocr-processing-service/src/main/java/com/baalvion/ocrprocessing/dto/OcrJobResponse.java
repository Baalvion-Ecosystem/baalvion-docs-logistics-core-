package com.baalvion.ocrprocessing.dto;

import com.baalvion.ocrprocessing.domain.OcrStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public class OcrJobResponse {

    private UUID id;
    private UUID documentId;
    private OcrStatus status;
    private String result;
    private String errorMessage;
    private String submittedBy;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;

    // ✅ CONSTRUCTORS
    public OcrJobResponse() {}

    public OcrJobResponse(UUID id, UUID documentId, OcrStatus status, String result,
                          String errorMessage, String submittedBy,
                          LocalDateTime createdAt, LocalDateTime completedAt) {
        this.id = id;
        this.documentId = documentId;
        this.status = status;
        this.result = result;
        this.errorMessage = errorMessage;
        this.submittedBy = submittedBy;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
    }

    // ✅ BUILDER
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private UUID documentId;
        private OcrStatus status;
        private String result;
        private String errorMessage;
        private String submittedBy;
        private LocalDateTime createdAt;
        private LocalDateTime completedAt;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder documentId(UUID documentId) { this.documentId = documentId; return this; }
        public Builder status(OcrStatus status) { this.status = status; return this; }
        public Builder result(String result) { this.result = result; return this; }
        public Builder errorMessage(String errorMessage) { this.errorMessage = errorMessage; return this; }
        public Builder submittedBy(String submittedBy) { this.submittedBy = submittedBy; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Builder completedAt(LocalDateTime completedAt) { this.completedAt = completedAt; return this; }

        public OcrJobResponse build() {
            return new OcrJobResponse(id, documentId, status, result,
                    errorMessage, submittedBy, createdAt, completedAt);
        }
    }

    // ✅ GETTERS & SETTERS
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getDocumentId() { return documentId; }
    public void setDocumentId(UUID documentId) { this.documentId = documentId; }

    public OcrStatus getStatus() { return status; }
    public void setStatus(OcrStatus status) { this.status = status; }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    public String getSubmittedBy() { return submittedBy; }
    public void setSubmittedBy(String submittedBy) { this.submittedBy = submittedBy; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
}