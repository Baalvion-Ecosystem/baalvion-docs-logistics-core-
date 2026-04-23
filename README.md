# Baalvion Docs & Logistics Core

Enterprise-grade, event-driven platform for global trade document management and logistics intelligence.

---

## Architecture

- **Style:** Microservices + Event-Driven (Apache Kafka)
- **Design:** Domain-Driven Design (DDD)
- **Security:** JWT Authentication + RBAC
- **Observability:** ELK + Prometheus + Grafana + OpenTelemetry

---

## Services

| Service                     | Port | Description                       |
| --------------------------- | ---- | --------------------------------- |
| document-vault-service      | 8081 | Store and manage documents        |
| document-generation-service | 8082 | Generate documents from templates |
| document-versioning-service | 8083 | Manage document versions          |
| ocr-processing-service      | 8084 | OCR processing for documents      |

---

## Event Flow

Document Uploaded
→ OCR Auto Triggered
→ OCR Completed
→ Version Auto Created

---

## Tech Stack

| Layer         | Technology               |
| ------------- | ------------------------ |
| Backend       | Java 17, Spring Boot 3.2 |
| Event Bus     | Apache Kafka             |
| Database      | PostgreSQL               |
| Cache         | Redis                    |
| Security      | JWT                      |
| Documentation | Swagger / OpenAPI        |
| Container     | Docker, Docker Compose   |
| Testing       | JUnit 5, Mockito         |

---

## Prerequisites

- Java 17
- Maven 3.8+
- Docker & Docker Compose

---

## Quick Start

1. Clone the repository:

```bash
git clone https://github.com/Baalvion-Ecosystem/baalvion-docs-logistics-core-.git
cd baalvion-docs-logistics-core-
```

2. Start infrastructure:

```bash
docker-compose -f infra/docker/docker-compose.yml up -d postgres kafka redis
```

3. Run a service:

```bash
cd services/document-vault-service
mvn spring-boot:run
```

---

## API Documentation

Once service is running, Swagger UI available at:

| Service                     | Swagger URL                           |
| --------------------------- | ------------------------------------- |
| document-vault-service      | http://localhost:8081/swagger-ui.html |
| document-generation-service | http://localhost:8082/swagger-ui.html |
| document-versioning-service | http://localhost:8083/swagger-ui.html |
| ocr-processing-service      | http://localhost:8084/swagger-ui.html |

---

## Security

All APIs are secured with JWT. Include token in header:
Authorization: Bearer <your-jwt-token>

Swagger UI endpoints are public.

---

## Running Tests

```bash
# Single service
cd services/document-vault-service
mvn test

# All services
mvn test --projects services/document-vault-service,services/document-generation-service,services/document-versioning-service,services/ocr-processing-service
```

---

## Project Structure

baalvion-docs-logistics-core/
├── docs/ # System design documents
├── infra/ # Docker, Kubernetes, Monitoring
├── platform/ # Observability, Security, Resilience
├── shared/ # Shared libraries and event models
├── gateway/ # API Gateway
├── event-bus/ # Kafka topics and schemas
└── services/ # Core microservices
├── document-vault-service
├── document-generation-service
├── document-versioning-service
└── ocr-processing-service

---

## Team

Built by Baalvion Engineering Team
