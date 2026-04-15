package com.baalvion.ocrprocessing.repository;

import com.baalvion.ocrprocessing.domain.OcrJob;
import com.baalvion.ocrprocessing.domain.OcrStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OcrJobRepository extends JpaRepository<OcrJob, UUID> {

    List<OcrJob> findByDocumentId(UUID documentId);

    List<OcrJob> findByStatus(OcrStatus status);

    List<OcrJob> findBySubmittedBy(String submittedBy);
}