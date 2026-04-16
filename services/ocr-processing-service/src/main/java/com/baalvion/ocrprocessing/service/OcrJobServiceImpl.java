package com.baalvion.ocrprocessing.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.baalvion.ocrprocessing.domain.OcrJob;
import com.baalvion.ocrprocessing.domain.OcrStatus;
import com.baalvion.ocrprocessing.dto.OcrJobRequest;
import com.baalvion.ocrprocessing.dto.OcrJobResponse;
import com.baalvion.ocrprocessing.exception.ResourceNotFoundException;
import com.baalvion.ocrprocessing.repository.OcrJobRepository;

@Service("ocrjobService")
public class OcrJobServiceImpl implements OcrjobService {

	private static final Logger log = LoggerFactory.getLogger(OcrJobServiceImpl.class);

	private final OcrJobRepository ocrJobRepository;

	public OcrJobServiceImpl(OcrJobRepository ocrJobRepository) {
		this.ocrJobRepository = ocrJobRepository;
	}

	@Override
	public OcrJobResponse submitOcrJob(OcrJobRequest request) {
		log.info("Submitting OCR job for documentId: {}", request.getDocumentId());

		OcrJob job = OcrJob.builder().documentId(request.getDocumentId()).submittedBy(request.getSubmittedBy())
				.status(OcrStatus.PENDING).build();

		OcrJob saved = ocrJobRepository.save(job);
		log.info("OCR job submitted with jobId: {}", saved.getOcrjobId());

		return mapToResponse(saved);
	}

	@Override
	public OcrJobResponse getJobStatus(UUID jobId) {
		log.info("Fetching OCR job status for jobId: {}", jobId);

		OcrJob job = ocrJobRepository.findByOcrjobId(jobId)
				.orElseThrow(() -> new ResourceNotFoundException("OCR job", "jobId", jobId));

		return mapToResponse(job);
	}

	@Override
	public OcrJobResponse processJob(UUID jobId) {
		log.info("Processing OCR job with jobId: {}", jobId);

		OcrJob job = ocrJobRepository.findByOcrjobId(jobId)
				.orElseThrow(() -> new ResourceNotFoundException("OCR job", "jobId", jobId));

		job.setStatus(OcrStatus.PROCESSING);
		ocrJobRepository.save(job);

		job.setStatus(OcrStatus.COMPLETED);
		job.setResult("OCR extracted text for documentId: " + job.getDocumentId());
		job.setCompletedAt(LocalDateTime.now());

		OcrJob completed = ocrJobRepository.save(job);
		log.info("OCR job completed with jobId: {}", jobId);

		return mapToResponse(completed);
	}

	@Override
	public List<OcrJobResponse> getJobsByDocumentId(UUID documentId) {
		log.info("Fetching OCR jobs for documentId: {}", documentId);

		return ocrJobRepository.findByDocumentId(documentId).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	private OcrJobResponse mapToResponse(OcrJob job) {
		return OcrJobResponse.builder().ocrjobId(job.getOcrjobId()).documentId(job.getDocumentId())
				.status(job.getStatus()).result(job.getResult()).errorMessage(job.getErrorMessage())
				.submittedBy(job.getSubmittedBy()).createdAt(job.getCreatedAt()).completedAt(job.getCompletedAt())
				.build();
	}
}