package com.baalvion.documentgeneration.repository;

import com.baalvion.documentgeneration.domain.DocumentTemplate;
import com.baalvion.documentgeneration.domain.TemplateStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocumentTemplateRepository extends JpaRepository<DocumentTemplate, Long> {
	
	Optional<DocumentTemplate> findByDocumentTemplateId(UUID documentTemplateId);

    List<DocumentTemplate> findByStatus(TemplateStatus status);

    List<DocumentTemplate> findByType(String type);

    List<DocumentTemplate> findByCreatedBy(String createdBy);
}