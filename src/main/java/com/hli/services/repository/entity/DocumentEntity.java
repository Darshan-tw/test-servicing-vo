package com.hli.services.repository.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "\"DOCUMENT\"")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentEntity {

    @Id
    @Column(name = "\"DOCUMENT_ID\"")
    private String documentId;

    @Column(name = "\"MEMBER_ID\"")
    private String memberId;

    @Column(name = "\"DOCUMENT_NAME\"")
    private String documentName;

    @Column(name = "\"FILE_SIZE\"")
    private String fileSize;

    @Column(name = "\"CLAIM_ID\"")
    private String claimId;

    @Nullable
    @Column(name = "\"UPLOADED_BY\"", nullable = true)
    private String uploadedBy;

    @Nullable
    @Column(name = "\"CREATED_BY\"", nullable = true)
    private String createdBy;

    @Nullable
    @Column(name = "\"OCR_AVAILABLE\"")
    private Boolean ocrAvailable;

    @CreationTimestamp
    @Column(name = "\"CREATED_AT\"", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "\"UPDATED_DATE\"", nullable = false)
    private Date updatedDate;
}