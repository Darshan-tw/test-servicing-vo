package com.hli.services.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@ToString
@Table(name = "vo_plan", schema = "public")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanEntity {

    @Id
    @Column(name = "PLAN_NUMBER")
    private String planNumber;

    @Column(name = "PLAN_NAME")
    private String planName;

    @Column(name = "LOAN_TYPE")
    private String loanType;

    @Column(name = "POLICY_NUMBER")
    private String policyNumber;

    @Column(name = "PLAN_EFFECTIVE_DATE")
    private LocalDate planEffectiveDate;

    @Column(name = "PRODUCT_CODE")
    private String productCode;

    @Column(name = "MIN_SUM_ASSURED")
    private BigDecimal minSumAssured;

    @Column(name = "MAX_SUM_ASSURED")
    private BigDecimal maxSumAssured;

    @Column(name = "MIN_AGE")
    private Integer minAge;

    @Column(name = "MAX_AGE")
    private Integer maxAge;

    @Column(name = "INSURANCE_CLASS")
    private String insuranceClass;

    @Column(name = "REFUND_ON_SURRENDER_FLAG")
    private String refundOnSurrenderFlag;

    @Column(name = "COVER_BASIS")
    private String coverBasis;

    @Column(name = "MIN_TERM")
    private Integer minTerm;

    @Column(name = "MAX_TERM")
    private Integer maxTerm;

    @Column(name = "NML_QUESTIONNAIRE")
    private String nmlQuestionnaire;

    @Column(name = "BELOW_ABOVE_NML_QUESTIONNAIRE")
    private String belowAboveNmlQuestionnaire;

    @Column(name = "PLAN_START_DATE")
    private LocalDate planStartDate;

    @Column(name = "PLAN_END_DATE")
    private LocalDate planEndDate;

    @Column(name = "BENEFIT_CODE")
    private String benefitCode;

    @Builder.Default
    @Column(name = "CREATED_BY")
    private String createdBy = "SYSTEM_USER";

    @Builder.Default
    @Column(name = "UPDATED_BY")
    private String updatedBy = "SYSTEM_USER";

    @CreationTimestamp
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;
}