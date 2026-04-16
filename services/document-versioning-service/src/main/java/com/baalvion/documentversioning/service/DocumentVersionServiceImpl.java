package com.baalvion.documentversioning.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.baalvion.documentversioning.domain.DocumentVersion;
import com.baalvion.documentversioning.domain.VersionStatus;
import com.baalvion.documentversioning.dto.DocumentVersionRequest;
import com.baalvion.documentversioning.dto.DocumentVersionResponse;
import com.baalvion.documentversioning.exception.ResourceNotFoundException;
import com.baalvion.documentversioning.repository.DocumentVersionRepository;

@Service("documentVersionService")
public class DocumentVersionServiceImpl implements DocumentVersionService {

	private static final Logger log = LoggerFactory.getLogger(DocumentVersionServiceImpl.class);

	private final DocumentVersionRepository versionRepository;

	public DocumentVersionServiceImpl(DocumentVersionRepository versionRepository) {
		this.versionRepository = versionRepository;
	}

	@Override
	public DocumentVersionResponse createVersion(DocumentVersionRequest request) {
		log.info("Creating new version for documentId: {}", request.getDocumentId());

		Integer nextVersionNumber = getNextVersionNumber(request.getDocumentId());

		DocumentVersion version = DocumentVersion.builder().documentVersionsId(UUID.randomUUID())
				.documentId(request.getDocumentId()).versionNumber(nextVersionNumber)
				.storagePath(request.getStoragePath()).modifiedBy(request.getModifiedBy())
				.changeSummary(request.getChangeSummary()).status(VersionStatus.ACTIVE).build();

		DocumentVersion saved = versionRepository.save(version);
		log.info("Version {} created for documentId: {}", nextVersionNumber, request.getDocumentId());

		return mapToResponse(saved);
	}

	@Override
	public List<DocumentVersionResponse> getAllVersions(UUID documentId) {
		log.info("Fetching all versions for documentId: {}", documentId);

		return versionRepository.findByDocumentIdOrderByVersionNumberDesc(documentId).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	@Override
	public DocumentVersionResponse getLatestVersion(UUID documentId) {
		log.info("Fetching latest version for documentId: {}", documentId);

		DocumentVersion version = versionRepository.findTopByDocumentIdOrderByVersionNumberDesc(documentId)
				.orElseThrow(() -> new ResourceNotFoundException("Version", "documentId", documentId));

		return mapToResponse(version);
	}

	@Override
	public DocumentVersionResponse getVersion(UUID versionId) {
		log.info("Fetching version with versionId: {}", versionId);

		DocumentVersion version = versionRepository.findByDocumentVersionsId(versionId)
				.orElseThrow(() -> new ResourceNotFoundException("Version", "versionId", versionId));

		return mapToResponse(version);
	}

	private Integer getNextVersionNumber(UUID documentId) {
		return versionRepository.findTopByDocumentIdOrderByVersionNumberDesc(documentId)
				.map(v -> v.getVersionNumber() + 1).orElse(1);
	}

	private DocumentVersionResponse mapToResponse(DocumentVersion version) {
		return DocumentVersionResponse.builder().documentVersionsId(version.getDocumentVersionsId())
				.documentId(version.getDocumentId()).versionNumber(version.getVersionNumber())
				.storagePath(version.getStoragePath()).modifiedBy(version.getModifiedBy())
				.changeSummary(version.getChangeSummary()).status(version.getStatus()).createdAt(version.getCreatedAt())
				.build();
	}
}