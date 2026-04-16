package com.baalvion.documentversioning.service;

import java.util.List;
import java.util.UUID;

import com.baalvion.documentversioning.dto.DocumentVersionRequest;
import com.baalvion.documentversioning.dto.DocumentVersionResponse;

public interface DocumentVersionService {

	public DocumentVersionResponse createVersion(DocumentVersionRequest request);

	public List<DocumentVersionResponse> getAllVersions(UUID documentId);

	public DocumentVersionResponse getLatestVersion(UUID documentId);

	public DocumentVersionResponse getVersion(UUID versionId);

}
