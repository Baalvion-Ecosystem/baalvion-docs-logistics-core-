# System Architecture — Baalvion Docs & Logistics Core

## Overview
Baalvion is an enterprise-grade, event-driven platform for global trade document management and logistics intelligence.

## Architecture Style
- Microservices Architecture
- Event-Driven (Apache Kafka)
- Domain-Driven Design (DDD)

## Core Domains
1. **Document Domain** — Vault, Generation, Versioning, OCR, Workflow, Signature
2. **Logistics Domain** — Shipping, Tracking, Customs, Route Optimization

## Tech Stack
| Layer | Technology |
|-------|-----------|
| Backend | Java 17, Spring Boot 3.x |
| Event Bus | Apache Kafka |
| Database | PostgreSQL, Redis, Elasticsearch |
| Storage | MinIO / AWS S3 |
| Observability | ELK, Prometheus, Grafana, OpenTelemetry |
| Security | JWT, RBAC |
| Container | Docker, Kubernetes |

## Communication
- Sync → REST APIs (versioned /api/v1/)
- Async → Kafka Events

## Failure Handling
- Retry (3 attempts)
- Circuit Breaker (Resilience4j)
- Dead Letter Queue (Kafka DLQ)
- Idempotency Keys
