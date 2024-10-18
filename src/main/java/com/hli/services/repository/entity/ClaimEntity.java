package com.hli.services.repository.entity;

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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "\"claim\"", schema = "public")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClaimEntity {

    @Id
    @Column(name = "\"COI_NUMBER\"")
    private String coiNumber;

    @Column(name = "\"CLAIMS_ID\"")
    private String claimsId;

    @Column(name = "\"LAN\"")
    private String lan;

    @Column(name = "\"POLICY_NUMBER\"")
    private String policyNumber;

    @Column(name = "\"PRODUCT_CODE\"")
    private String productCode;

    @Column(name = "\"CLAIM_TYPE\"")
    private String claimType;

    @Column(name = "\"CAUSE_OF_EVENT\"")
    private String causeOfEvent;

    @Column(name = "\"DATE_OF_EVENT\"")
    private LocalDate dateOfEvent;

    @Column(name = "\"DATE_OF_REPORTING\"")
    private LocalDate dateOfReporting;

    @Column(name = "\"DATE_OF_ACCIDENT_OR_DIAGNOSIS\"")
    private LocalDate dateOfAccidentOrDiagnosis;

    @Column(name = "\"IS_MEMBER_DEAD\"")
    private String isMemberDead;

    @Column(name = "\"SUM_ASSURED\"")
    private BigDecimal sumAssured;

    @Column(name = "\"ORIGINAL_AMOUNT_OF_LOAN\"")
    private BigDecimal originalAmountofloan;

    @Column(name = "\"OUTSTANDING_LOAN_AMOUNT\"")
    private BigDecimal outstandingLoanAmount;

    @Column(name = "\"RECOVERIES\"")
    private BigDecimal recoveries;

    @Column(name = "\"BALANCE_CLAIM_AMOUNT\"")
    private BigDecimal balanceClaimAmount;

    @Column(name = "\"DECLARATION1\"")
    private String declaration1;

    @Column(name = "\"DECLARATION2\"")
    private String declaration2;

    @Column(name = "\"DECLARATION3\"")
    private String declaration3;

    @Builder.Default
    @Column(name = "\"CREATED_BY\"")
    private String createdBy = "SYSTEM_USER";

    @Builder.Default
    @Column(name = "\"UPDATED_BY\"")
    private String updatedBy = "SYSTEM_USER";

    @CreationTimestamp
    @Column(name = "\"CREATED_AT\"", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "\"UPDATED_AT\"", nullable = false)
    private LocalDateTime updatedAt;
}