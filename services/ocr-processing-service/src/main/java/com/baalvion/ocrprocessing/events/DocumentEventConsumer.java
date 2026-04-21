package com.baalvion.ocrprocessing.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.baalvion.events.DocumentUploadedEvent;
import com.baalvion.ocrprocessing.dto.OcrJobRequest;
import com.baalvion.ocrprocessing.service.OcrjobService;

@Component
public class DocumentEventConsumer {

	private static final Logger log = LoggerFactory.getLogger(DocumentEventConsumer.class);

	private final OcrjobService ocrJobService;

	public DocumentEventConsumer(OcrjobService ocrJobService) {
		this.ocrJobService = ocrJobService;
	}

	@KafkaListener(topics = "document.uploaded", groupId = "ocr-processing-group")
	public void handleDocumentUploadedEvent(DocumentUploadedEvent event) {
		log.info("Received DocumentUploadedEvent for documentId: {}", event.getDocumentId());

		try {
			OcrJobRequest request = new OcrJobRequest();
			request.setDocumentId(event.getDocumentId());
			request.setSubmittedBy(event.getUploadedBy());

			ocrJobService.submitOcrJob(request);
			log.info("OCR job auto-submitted for documentId: {}", event.getDocumentId());

		} catch (Exception e) {
			log.error("Failed to process DocumentUploadedEvent: {}", e.getMessage());
		}
	}
}