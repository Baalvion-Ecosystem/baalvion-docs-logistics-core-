package com.baalvion.documentvault.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.baalvion.documentvault.domain.Document;
import com.baalvion.documentvault.domain.DocumentStatus;
import com.baalvion.documentvault.dto.DocumentRequest;
import com.baalvion.documentvault.dto.DocumentResponse;
import com.baalvion.documentvault.events.DocumentEventProducer;
import com.baalvion.documentvault.exception.ResourceNotFoundException;
import com.baalvion.documentvault.repository.DocumentRepository;

@ExtendWith(MockitoExtension.class)
class DocumentServiceTest {

	@Mock
	private DocumentRepository documentRepository;

	@Mock
	private DocumentEventProducer eventProducer;

	@InjectMocks
	private DocumentServiceImpl documentService;

	private Document document;
	private DocumentRequest request;
	private UUID documentId;

	@BeforeEach
	void setUp() {
		documentId = UUID.randomUUID();

		document = Document.builder().documentId(documentId).name("Test Document").type("PDF")
				.storagePath("/storage/test.pdf").uploadedBy("testuser").status(DocumentStatus.ACTIVE)
				.createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).build();

		request = new DocumentRequest();
		request.setName("Test Document");
		request.setType("PDF");
		request.setStoragePath("/storage/test.pdf");
		request.setUploadedBy("testuser");
	}

	@Test
	void uploadDocument_ShouldReturnDocumentResponse() {
		when(documentRepository.save(any(Document.class))).thenReturn(document);
		doNothing().when(eventProducer).publishDocumentUploadedEvent(any());

		DocumentResponse response = documentService.uploadDocument(request);

		assertNotNull(response);
		assertEquals("Test Document", response.getName());
		assertEquals("PDF", response.getType());
		assertEquals(DocumentStatus.ACTIVE, response.getStatus());
		verify(documentRepository, times(1)).save(any(Document.class));
		verify(eventProducer, times(1)).publishDocumentUploadedEvent(any());
	}

	@Test
	void getDocument_ShouldReturnDocumentResponse_WhenDocumentExists() {
		when(documentRepository.findByDocumentId(documentId)).thenReturn(Optional.of(document));

		DocumentResponse response = documentService.getDocument(documentId);

		assertNotNull(response);
		assertEquals(documentId, response.getDocumentId());
		assertEquals("Test Document", response.getName());
		verify(documentRepository, times(1)).findByDocumentId(documentId);
	}

	@Test
	void getDocument_ShouldThrowException_WhenDocumentNotFound() {
		when(documentRepository.findByDocumentId(documentId)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			documentService.getDocument(documentId);
		});

		verify(documentRepository, times(1)).findByDocumentId(documentId);
	}

	@Test
	void getAllDocuments_ShouldReturnListOfDocuments() {
		when(documentRepository.findAll()).thenReturn(List.of(document));

		List<DocumentResponse> responses = documentService.getAllDocuments();

		assertNotNull(responses);
		assertEquals(1, responses.size());
		assertEquals("Test Document", responses.get(0).getName());
		verify(documentRepository, times(1)).findAll();
	}

	@Test
	void deleteDocument_ShouldSetStatusToDeleted() {
		when(documentRepository.findByDocumentId(documentId)).thenReturn(Optional.of(document));
		when(documentRepository.save(any(Document.class))).thenReturn(document);

		documentService.deleteDocument(documentId);

		verify(documentRepository, times(1)).findByDocumentId(documentId);
		verify(documentRepository, times(1)).save(any(Document.class));
		assertEquals(DocumentStatus.DELETED, document.getStatus());
	}

	@Test
	void deleteDocument_ShouldThrowException_WhenDocumentNotFound() {
		when(documentRepository.findByDocumentId(documentId)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			documentService.deleteDocument(documentId);
		});

		verify(documentRepository, times(1)).findByDocumentId(documentId);
	}
}