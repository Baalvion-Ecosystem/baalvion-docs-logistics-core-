package com.baalvion.ocrprocessing.service;

import com.baalvion.ocrprocessing.domain.OcrJob;
import com.baalvion.ocrprocessing.domain.OcrStatus;
import com.baalvion.ocrprocessing.dto.OcrJobRequest;
import com.baalvion.ocrprocessing.dto.OcrJobResponse;
import com.baalvion.ocrprocessing.repository.OcrJobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OcrJobService {

    private static final Logger log = LoggerFactory.getLogger(OcrJobService.class);

    private final OcrJobRepository ocrJobRepository;

    public OcrJobService(OcrJobRepository ocrJobRepository) {
        this.ocrJobRepository = ocrJobRepository;
    }

    public OcrJobResponse submitOcrJob(OcrJobRequest request) {
        log.info("Submitting OCR job for documentId: {}", request.getDocumentId());

        OcrJob job = OcrJob.builder()
                .documentId(request.getDocumentId())
                .submittedBy(request.getSubmittedBy())
                .status(OcrStatus.PENDING)
                .build();

        OcrJob saved = ocrJobRepository.save(job);
        log.info("OCR job submitted with id: {}", saved.getId());

        return mapToResponse(saved);
    }

    public OcrJobResponse getJobStatus(UUID jobId) {
        log.info("Fetching OCR job status for id: {}", jobId);

        OcrJob job = ocrJobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("OCR job not found with id: " + jobId));

        return mapToResponse(job);
    }

    public OcrJobResponse processJob(UUID jobId) {
        log.info("Processing OCR job with id: {}", jobId);

        OcrJob job = ocrJobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("OCR job not found with id: " + jobId));

        job.setStatus(OcrStatus.PROCESSING);
        ocrJobRepository.save(job);

        // Simulated OCR processing
        job.setStatus(OcrStatus.COMPLETED);
        job.setResult("OCR extracted text for documentId: " + job.getDocumentId());
        job.setCompletedAt(LocalDateTime.now());

        OcrJob completed = ocrJobRepository.save(job);
        log.info("OCR job completed with id: {}", jobId);

        return mapToResponse(completed);
    }

    public List<OcrJobResponse> getJobsByDocumentId(UUID documentId) {
        log.info("Fetching OCR jobs for documentId: {}", documentId);

        return ocrJobRepository.findByDocumentId(documentId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private OcrJobResponse mapToResponse(OcrJob job) {
        return OcrJobResponse.builder()
                .id(job.getId())
                .documentId(job.getDocumentId())
                .status(job.getStatus())
                .result(job.getResult())
                .errorMessage(job.getErrorMessage())
                .submittedBy(job.getSubmittedBy())
                .createdAt(job.getCreatedAt())
                .completedAt(job.getCompletedAt())
                .build();
    }
}