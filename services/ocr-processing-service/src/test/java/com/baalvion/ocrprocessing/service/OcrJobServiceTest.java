package com.baalvion.ocrprocessing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
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

import com.baalvion.ocrprocessing.domain.OcrJob;
import com.baalvion.ocrprocessing.domain.OcrStatus;
import com.baalvion.ocrprocessing.dto.OcrJobRequest;
import com.baalvion.ocrprocessing.dto.OcrJobResponse;
import com.baalvion.ocrprocessing.events.OcrEventProducer;
import com.baalvion.ocrprocessing.exception.ResourceNotFoundException;
import com.baalvion.ocrprocessing.repository.OcrJobRepository;

@ExtendWith(MockitoExtension.class)
class OcrJobServiceTest {

	@Mock
	private OcrJobRepository ocrJobRepository;

	@Mock
	private OcrEventProducer ocrEventProducer;

	@InjectMocks
	private OcrJobServiceImpl ocrJobService;

	private OcrJob ocrJob;
	private OcrJobRequest request;
	private UUID jobId;
	private UUID documentId;

	@BeforeEach
	void setUp() {
		jobId = UUID.randomUUID();
		documentId = UUID.randomUUID();

		ocrJob = OcrJob.builder().ocrjobId(jobId).documentId(documentId).status(OcrStatus.PENDING)
				.submittedBy("testuser").createdAt(LocalDateTime.now()).build();

		request = new OcrJobRequest();
		request.setDocumentId(documentId);
		request.setSubmittedBy("testuser");
	}

	@Test
	void submitOcrJob_ShouldReturnResponse_WhenRequestIsValid() {
		when(ocrJobRepository.save(any(OcrJob.class))).thenReturn(ocrJob);

		OcrJobResponse response = ocrJobService.submitOcrJob(request);

		assertNotNull(response);
		assertEquals(documentId, response.getDocumentId());
		assertEquals(OcrStatus.PENDING, response.getStatus());
		verify(ocrJobRepository, times(1)).save(any(OcrJob.class));
	}

	@Test
	void getJobStatus_ShouldReturnResponse_WhenJobExists() {
		when(ocrJobRepository.findByOcrjobId(jobId)).thenReturn(Optional.of(ocrJob));

		OcrJobResponse response = ocrJobService.getJobStatus(jobId);

		assertNotNull(response);
		assertEquals(jobId, response.getOcrjobId());
		assertEquals(OcrStatus.PENDING, response.getStatus());
		verify(ocrJobRepository, times(1)).findByOcrjobId(jobId);
	}

	@Test
	void getJobStatus_ShouldThrowException_WhenJobNotFound() {
		when(ocrJobRepository.findByOcrjobId(jobId)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			ocrJobService.getJobStatus(jobId);
		});
	}

	@Test
	void processJob_ShouldCompleteJob_AndPublishEvent() {
		when(ocrJobRepository.findByOcrjobId(jobId)).thenReturn(Optional.of(ocrJob));
		when(ocrJobRepository.save(any(OcrJob.class))).thenReturn(ocrJob);
		doNothing().when(ocrEventProducer).publishOcrCompletedEvent(any());

		OcrJobResponse response = ocrJobService.processJob(jobId);

		assertNotNull(response);
		verify(ocrJobRepository, times(2)).save(any(OcrJob.class));
		verify(ocrEventProducer, times(1)).publishOcrCompletedEvent(any());
	}

	@Test
	void processJob_ShouldThrowException_WhenJobNotFound() {
		when(ocrJobRepository.findByOcrjobId(jobId)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			ocrJobService.processJob(jobId);
		});
	}

	@Test
	void getJobsByDocumentId_ShouldReturnList_WhenJobsExist() {
		when(ocrJobRepository.findByDocumentId(documentId)).thenReturn(List.of(ocrJob));

		List<OcrJobResponse> responses = ocrJobService.getJobsByDocumentId(documentId);

		assertNotNull(responses);
		assertEquals(1, responses.size());
		assertEquals(documentId, responses.get(0).getDocumentId());
		verify(ocrJobRepository, times(1)).findByDocumentId(documentId);
	}
}