package com.baalvion.ocrprocessing.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baalvion.ocrprocessing.domain.OcrJob;
import com.baalvion.ocrprocessing.domain.OcrStatus;

@Repository
public interface OcrJobRepository extends JpaRepository<OcrJob, Long> {

	Optional<OcrJob> findByOcrjobId(UUID ocrjobId);

	List<OcrJob> findByDocumentId(UUID documentId);

	List<OcrJob> findByStatus(OcrStatus status);

	List<OcrJob> findBySubmittedBy(String submittedBy);
}