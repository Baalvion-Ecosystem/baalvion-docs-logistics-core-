package com.baalvion.documentversioning.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.baalvion.documentversioning.dto.DocumentVersionRequest;
import com.baalvion.documentversioning.service.DocumentVersionService;
import com.baalvion.events.OcrCompletedEvent;

@Component
public class OcrEventConsumer {

	private static final Logger log = LoggerFactory.getLogger(OcrEventConsumer.class);

	private final DocumentVersionService versionService;

	public OcrEventConsumer(DocumentVersionService versionService) {
		this.versionService = versionService;
	}

	@KafkaListener(topics = "document.ocr.completed", groupId = "document-versioning-group")
	public void handleOcrCompletedEvent(OcrCompletedEvent event) {
		log.info("Received OcrCompletedEvent for documentId: {}", event.getDocumentId());

		try {
			DocumentVersionRequest request = new DocumentVersionRequest();
			request.setDocumentId(event.getDocumentId());
			request.setStoragePath("ocr/result/" + event.getDocumentId());
			request.setModifiedBy("ocr-system");
			request.setChangeSummary("OCR completed - " + event.getResult());

			versionService.createVersion(request);
			log.info("Version auto-created after OCR for documentId: {}", event.getDocumentId());

		} catch (Exception e) {
			log.error("Failed to process OcrCompletedEvent: {}", e.getMessage());
		}
	}
}