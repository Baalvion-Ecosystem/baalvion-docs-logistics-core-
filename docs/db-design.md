# Database Design

## Rules
- Each service owns its own database (no shared DB)
- No direct DB access between services
- All communication via Events or APIs

## Service → Database Mapping
| Service | Database | Reason |
|---------|----------|--------|
| document-vault-service | PostgreSQL + MinIO/S3 | Metadata + File Storage |
| document-generation-service | PostgreSQL | Template & Job tracking |
| document-versioning-service | PostgreSQL | Version history |
| ocr-processing-service | PostgreSQL + Redis | Jobs + Cache |

## document-vault-service Schema
```sql
CREATE TABLE documents (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    type VARCHAR(100) NOT NULL,
    storage_path TEXT NOT NULL,
    uploaded_by VARCHAR(255) NOT NULL,
    status VARCHAR(50) DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);
```

## ocr-processing-service Schema
```sql
CREATE TABLE ocr_jobs (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    document_id UUID NOT NULL,
    status VARCHAR(50) DEFAULT 'PENDING',
    result TEXT,
    created_at TIMESTAMP DEFAULT NOW(),
    completed_at TIMESTAMP
);
```
