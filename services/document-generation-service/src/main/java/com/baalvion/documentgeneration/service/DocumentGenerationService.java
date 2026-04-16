package com.baalvion.documentgeneration.service;

import java.util.UUID;

import com.baalvion.documentgeneration.dto.DocumentGenerationRequest;
import com.baalvion.documentgeneration.dto.DocumentGenerationResponse;

public interface DocumentGenerationService {

	public DocumentGenerationResponse generateDocument(DocumentGenerationRequest request);

	public DocumentGenerationResponse getTemplate(UUID documentTemplateId);

}
