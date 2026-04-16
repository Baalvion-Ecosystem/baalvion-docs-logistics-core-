package com.baalvion.ocrprocessing.service;

import java.util.List;
import java.util.UUID;

import com.baalvion.ocrprocessing.dto.OcrJobRequest;
import com.baalvion.ocrprocessing.dto.OcrJobResponse;

public interface OcrjobService {

	public OcrJobResponse submitOcrJob(OcrJobRequest request);

	public OcrJobResponse getJobStatus(UUID jobId);

	public OcrJobResponse processJob(UUID jobId);

	public List<OcrJobResponse> getJobsByDocumentId(UUID documentId);

}
