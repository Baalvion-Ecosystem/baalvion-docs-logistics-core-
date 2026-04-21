package com.baalvion.ocrprocessing.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.baalvion.events.OcrCompletedEvent;

@Component
public class OcrEventProducer {

	private static final Logger log = LoggerFactory.getLogger(OcrEventProducer.class);

	private static final String OCR_COMPLETED_TOPIC = "document.ocr.completed";

	private final KafkaTemplate<String, Object> kafkaTemplate;

	public OcrEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void publishOcrCompletedEvent(OcrCompletedEvent event) {
		log.info("Publishing OcrCompletedEvent for documentId: {}", event.getDocumentId());
		kafkaTemplate.send(OCR_COMPLETED_TOPIC, event.getDocumentId().toString(), event);
		log.info("OcrCompletedEvent published successfully for documentId: {}", event.getDocumentId());
	}
}