package com.baalvion.documentvault.service;

import java.util.List;
import java.util.UUID;

import com.baalvion.documentvault.dto.DocumentRequest;
import com.baalvion.documentvault.dto.DocumentResponse;

public interface DocumentService {

	public DocumentResponse uploadDocument(DocumentRequest request);

	public DocumentResponse getDocument(UUID documentId);

	public List<DocumentResponse> getAllDocuments();

	public void deleteDocument(UUID documentId);

}
