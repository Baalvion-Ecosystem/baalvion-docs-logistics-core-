package com.baalvion.documentversioning.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

import com.baalvion.documentversioning.domain.DocumentVersion;
import com.baalvion.documentversioning.domain.VersionStatus;
import com.baalvion.documentversioning.dto.DocumentVersionRequest;
import com.baalvion.documentversioning.dto.DocumentVersionResponse;
import com.baalvion.documentversioning.exception.ResourceNotFoundException;
import com.baalvion.documentversioning.repository.DocumentVersionRepository;

@ExtendWith(MockitoExtension.class)
class DocumentVersionServiceTest {

	@Mock
	private DocumentVersionRepository versionRepository;

	@InjectMocks
	private DocumentVersionServiceImpl versionService;

	private DocumentVersion version;
	private DocumentVersionRequest request;
	private UUID documentId;
	private UUID versionId;

	@BeforeEach
	void setUp() {
		documentId = UUID.randomUUID();
		versionId = UUID.randomUUID();

		version = DocumentVersion.builder().documentVersionsId(versionId).documentId(documentId).versionNumber(1)
				.storagePath("/storage/v1/test.pdf").modifiedBy("testuser").changeSummary("Initial version")
				.status(VersionStatus.ACTIVE).createdAt(LocalDateTime.now()).build();

		request = new DocumentVersionRequest();
		request.setDocumentId(documentId);
		request.setStoragePath("/storage/v1/test.pdf");
		request.setModifiedBy("testuser");
		request.setChangeSummary("Initial version");
	}

	@Test
	void createVersion_ShouldReturnResponse_WhenRequestIsValid() {
		when(versionRepository.findTopByDocumentIdOrderByVersionNumberDesc(documentId)).thenReturn(Optional.empty());
		when(versionRepository.save(any(DocumentVersion.class))).thenReturn(version);

		DocumentVersionResponse response = versionService.createVersion(request);

		assertNotNull(response);
		assertEquals(documentId, response.getDocumentId());
		assertEquals(1, response.getVersionNumber());
		verify(versionRepository, times(1)).save(any(DocumentVersion.class));
	}

	@Test
	void createVersion_ShouldIncrementVersionNumber() {
		when(versionRepository.findTopByDocumentIdOrderByVersionNumberDesc(documentId))
				.thenReturn(Optional.of(version));
		when(versionRepository.save(any(DocumentVersion.class))).thenReturn(version);

		versionService.createVersion(request);

		verify(versionRepository, times(1)).save(any(DocumentVersion.class));
	}

	@Test
	void getAllVersions_ShouldReturnList_WhenVersionsExist() {
		when(versionRepository.findByDocumentIdOrderByVersionNumberDesc(documentId)).thenReturn(List.of(version));

		List<DocumentVersionResponse> responses = versionService.getAllVersions(documentId);

		assertNotNull(responses);
		assertEquals(1, responses.size());
		assertEquals(documentId, responses.get(0).getDocumentId());
		verify(versionRepository, times(1)).findByDocumentIdOrderByVersionNumberDesc(documentId);
	}

	@Test
	void getLatestVersion_ShouldReturnResponse_WhenVersionExists() {
		when(versionRepository.findTopByDocumentIdOrderByVersionNumberDesc(documentId))
				.thenReturn(Optional.of(version));

		DocumentVersionResponse response = versionService.getLatestVersion(documentId);

		assertNotNull(response);
		assertEquals(documentId, response.getDocumentId());
		assertEquals(1, response.getVersionNumber());
	}

	@Test
	void getLatestVersion_ShouldThrowException_WhenNoVersionFound() {
		when(versionRepository.findTopByDocumentIdOrderByVersionNumberDesc(documentId)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			versionService.getLatestVersion(documentId);
		});
	}

	@Test
	void getVersion_ShouldReturnResponse_WhenVersionExists() {
		when(versionRepository.findByDocumentVersionsId(versionId)).thenReturn(Optional.of(version));

		DocumentVersionResponse response = versionService.getVersion(versionId);

		assertNotNull(response);
		assertEquals(versionId, response.getDocumentVersionsId());
		verify(versionRepository, times(1)).findByDocumentVersionsId(versionId);
	}

	@Test
	void getVersion_ShouldThrowException_WhenVersionNotFound() {
		when(versionRepository.findByDocumentVersionsId(versionId)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			versionService.getVersion(versionId);
		});
	}
}