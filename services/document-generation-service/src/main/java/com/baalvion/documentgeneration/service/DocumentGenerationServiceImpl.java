package com.baalvion.documentgeneration.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.baalvion.documentgeneration.domain.DocumentTemplate;
import com.baalvion.documentgeneration.dto.DocumentGenerationRequest;
import com.baalvion.documentgeneration.dto.DocumentGenerationResponse;
import com.baalvion.documentgeneration.exception.ResourceNotFoundException;
import com.baalvion.documentgeneration.repository.DocumentTemplateRepository;

@Service("documentGenerationService")
public class DocumentGenerationServiceImpl implements DocumentGenerationService {

	private static final Logger log = LoggerFactory.getLogger(DocumentGenerationServiceImpl.class);

	private final DocumentTemplateRepository templateRepository;

	public DocumentGenerationServiceImpl(DocumentTemplateRepository templateRepository) {
		this.templateRepository = templateRepository;
	}

	@Override
	public DocumentGenerationResponse generateDocument(DocumentGenerationRequest request) {
		log.info("Generating document for documentTemplateId: {}", request.getDocumentTemplateId());

		DocumentTemplate template = templateRepository.findByDocumentTemplateId(request.getDocumentTemplateId())
				.orElseThrow(() -> new ResourceNotFoundException("Document Template", "documentTemplateId",
						request.getDocumentTemplateId()));

		String generatedContent = processTemplate(template.getTemplateContent(), request.getPlaceholders());

		log.info("Document generated successfully for documentTemplateId: {}", request.getDocumentTemplateId());

		return DocumentGenerationResponse.builder().documentTemplateId(UUID.randomUUID())
				.generatedBy(request.getGeneratedBy()).generatedContent(generatedContent).status("GENERATED")
				.createdAt(LocalDateTime.now()).build();
	}

	@Override
	public DocumentGenerationResponse getTemplate(UUID documentTemplateId) {
		log.info("Fetching template with documentTemplateId: {}", documentTemplateId);

		DocumentTemplate template = templateRepository.findByDocumentTemplateId(documentTemplateId).orElseThrow(
				() -> new ResourceNotFoundException("Document Template", "documentTemplateId", documentTemplateId));

		return DocumentGenerationResponse.builder().documentTemplateId(template.getDocumentTemplateId())
				.generatedBy(template.getCreatedBy()).generatedContent(template.getTemplateContent())
				.status(template.getStatus().name()).createdAt(template.getCreatedAt()).build();
	}

	private String processTemplate(String templateContent, Map<String, String> placeholders) {
		if (placeholders == null || placeholders.isEmpty()) {
			return templateContent;
		}
		String processed = templateContent;
		for (Map.Entry<String, String> entry : placeholders.entrySet()) {
			processed = processed.replace("{{" + entry.getKey() + "}}", entry.getValue());
		}
		return processed;
	}
}