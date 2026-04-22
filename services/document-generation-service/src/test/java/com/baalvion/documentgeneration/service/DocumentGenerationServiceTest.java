package com.baalvion.documentgeneration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.baalvion.documentgeneration.domain.DocumentTemplate;
import com.baalvion.documentgeneration.domain.TemplateStatus;
import com.baalvion.documentgeneration.dto.DocumentGenerationRequest;
import com.baalvion.documentgeneration.dto.DocumentGenerationResponse;
import com.baalvion.documentgeneration.exception.ResourceNotFoundException;
import com.baalvion.documentgeneration.repository.DocumentTemplateRepository;

@ExtendWith(MockitoExtension.class)
class DocumentGenerationServiceTest {

	@Mock
	private DocumentTemplateRepository templateRepository;

	@InjectMocks
	private DocumentGenerationServiceImpl documentGenerationService;

	private DocumentTemplate template;
	private DocumentGenerationRequest request;
	private UUID documentTemplateId;

	@BeforeEach
	void setUp() {
		documentTemplateId = UUID.randomUUID();

		template = DocumentTemplate.builder().documentTemplateId(documentTemplateId).name("Test Template").type("PDF")
				.templateContent("Hello {{name}}, your order {{orderId}} is ready!").status(TemplateStatus.ACTIVE)
				.createdBy("testuser").createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).build();

		Map<String, String> placeholders = new HashMap<>();
		placeholders.put("name", "John");
		placeholders.put("orderId", "ORD-123");

		request = new DocumentGenerationRequest();
		request.setDocumentTemplateId(documentTemplateId);
		request.setGeneratedBy("testuser");
		request.setPlaceholders(placeholders);
	}

	@Test
	void generateDocument_ShouldReturnResponse_WhenTemplateExists() {
		when(templateRepository.findByDocumentTemplateId(documentTemplateId)).thenReturn(Optional.of(template));

		DocumentGenerationResponse response = documentGenerationService.generateDocument(request);

		assertNotNull(response);
		assertNotNull(response.getDocumentTemplateId());
		assertEquals("GENERATED", response.getStatus());
		assertTrue(response.getGeneratedContent().contains("John"));
		assertTrue(response.getGeneratedContent().contains("ORD-123"));
		verify(templateRepository, times(1)).findByDocumentTemplateId(documentTemplateId);
	}

	@Test
	void generateDocument_ShouldThrowException_WhenTemplateNotFound() {
		when(templateRepository.findByDocumentTemplateId(documentTemplateId)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			documentGenerationService.generateDocument(request);
		});

		verify(templateRepository, times(1)).findByDocumentTemplateId(documentTemplateId);
	}

	@Test
	void getTemplate_ShouldReturnResponse_WhenTemplateExists() {
		when(templateRepository.findByDocumentTemplateId(documentTemplateId)).thenReturn(Optional.of(template));

		DocumentGenerationResponse response = documentGenerationService.getTemplate(documentTemplateId);

		assertNotNull(response);
		assertEquals(documentTemplateId, response.getDocumentTemplateId());
		assertEquals("testuser", response.getGeneratedBy());
		verify(templateRepository, times(1)).findByDocumentTemplateId(documentTemplateId);
	}

	@Test
	void getTemplate_ShouldThrowException_WhenTemplateNotFound() {
		when(templateRepository.findByDocumentTemplateId(documentTemplateId)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			documentGenerationService.getTemplate(documentTemplateId);
		});

		verify(templateRepository, times(1)).findByDocumentTemplateId(documentTemplateId);
	}
}