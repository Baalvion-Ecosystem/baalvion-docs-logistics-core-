# API Contracts

## Standards
- Base URL: `/api/v1/`
- Format: JSON
- Auth: Bearer JWT Token

## Standard Response Format
```json
{
  "status": "success | error",
  "data": {},
  "message": "string",
  "timestamp": "ISO-8601"
}
```

## Document Vault Service
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/v1/documents | Upload document |
| GET | /api/v1/documents/{id} | Get document |
| DELETE | /api/v1/documents/{id} | Delete document |
| GET | /api/v1/documents | List all documents |

## OCR Processing Service
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/v1/ocr/process | Submit for OCR |
| GET | /api/v1/ocr/status/{jobId} | Check OCR status |
| GET | /api/v1/ocr/result/{jobId} | Get OCR result |

## Document Versioning Service
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/v1/versions/{docId} | Create new version |
| GET | /api/v1/versions/{docId} | Get all versions |
| GET | /api/v1/versions/{docId}/{versionId} | Get specific version |
