package com.hli.services.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanDetails {
    private String loanType;
    private String lan;
    private String policyNumber;
    private String planNumber;
    private String panNumber;
    private BigDecimal originalLoanAmount;
    private BigDecimal sumAssured;
    private BigDecimal minSumAssured;
    private BigDecimal maxSumAssured;
    private Integer minTerm;
    private Integer maxTerm;
    private Integer policyTerm;
    private LocalDate riskCommencementDate;
}