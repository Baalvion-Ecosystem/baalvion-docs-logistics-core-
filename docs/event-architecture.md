# Event Architecture

## Event Bus: Apache Kafka

## Core Topics

Topic | Producer | Consumer
document.uploaded | document-vault-service | ocr-processing-service
document.ocr.completed | ocr-processing-service | document-vault-service
document.version.created | document-versioning-service | document-vault-service

## Event Flow

Document Uploaded -> OCR Processing -> OCR Completed -> Version Created -> Notification Sent

## Event Structure

eventId, eventType, timestamp, version, payload

## Failure Handling

- Retry 3 attempts
- DLQ for failed events
- Idempotency via eventId

## Event Structure (Standard)

eventId: uuid
eventType: document.uploaded
timestamp: ISO-8601
version: 1.0
payload: {}

## Failure Handling

- Retry: 3 attempts with exponential backoff
- DLQ: topic-name.dlq for failed events
- Idempotency: eventId used for deduplication
