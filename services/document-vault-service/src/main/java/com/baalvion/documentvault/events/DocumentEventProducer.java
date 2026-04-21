package com.baalvion.documentvault.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.baalvion.events.DocumentUploadedEvent;

@Component
public class DocumentEventProducer {

	private static final Logger log = LoggerFactory.getLogger(DocumentEventProducer.class);

	private static final String DOCUMENT_UPLOADED_TOPIC = "document.uploaded";

	private final KafkaTemplate<String, Object> kafkaTemplate;

	public DocumentEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void publishDocumentUploadedEvent(DocumentUploadedEvent event) {
		log.info("Publishing DocumentUploadedEvent for documentId: {}", event.getDocumentId());
		kafkaTemplate.send(DOCUMENT_UPLOADED_TOPIC, event.getDocumentId().toString(), event);
		log.info("DocumentUploadedEvent published successfully for documentId: {}", event.getDocumentId());
	}
}