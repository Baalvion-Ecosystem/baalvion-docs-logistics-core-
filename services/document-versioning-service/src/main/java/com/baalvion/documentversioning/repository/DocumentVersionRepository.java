package com.baalvion.documentversioning.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baalvion.documentversioning.domain.DocumentVersion;
import com.baalvion.documentversioning.domain.VersionStatus;

@Repository
public interface DocumentVersionRepository extends JpaRepository<DocumentVersion, Long> {

	Optional<DocumentVersion> findByDocumentVersionsId(UUID documentVersionsId);

	List<DocumentVersion> findByDocumentIdOrderByVersionNumberDesc(UUID documentId);

	Optional<DocumentVersion> findTopByDocumentIdOrderByVersionNumberDesc(UUID documentId);

	List<DocumentVersion> findByDocumentIdAndStatus(UUID documentId, VersionStatus status);
}