package com.hli.services.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@Data
@Entity
@Table(name = "\"VO_MEMBER_DETAILS\"")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDetailsEntity {
    @Id
    @Column(name = "\"MEMBER_NUMBER\"")
    private String memberNumber;

    @Column(name = "\"COI_NUMBER\"")
    private String coiNumber;

    @ManyToOne
    @JoinColumn(name = "\"PLAN_NUMBER\"")
    private PlanEntity plan;

    @Column(name = "\"POLICY_NUMBER\"")
    private String policyNumber;

    @Column(name = "\"PARTNER\"")
    private String partner;

    @Column(name = "\"EFFECTIVE_DATE\"")
    @Temporal(TemporalType.DATE)
    private Date effectiveDate;

    @Column(name = "\"MPH_ID\"")
    private String mphId;

    @Column(name = "\"MEMBER_TYPE\"")
    private String memberType;

    @Column(name = "\"LAN\"")
    private String lan;

    @Column(name = "\"CLIENT_NUMBER\"")
    private String clientNumber;

    @Column(name = "\"SUM_ASSURED\"")
    private Integer sumAssured;

    @Column(name = "\"START_DATE\"")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "\"DOB\"")
    @Temporal(TemporalType.DATE)
    private Date dob;

    @Column(name = "\"COVER_TYPE_FLAG\"")
    private String coverTypeFlag;

    @Column(name = "\"PREMIUM_TYPE\"")
    private String premiumType;

    @Column(name = "\"PREMIUM_FREQUENCY\"")
    private String premiumFrequency;

    @Column(name = "\"RISK_TERM\"")
    private Byte riskTerm;

    @Column(name = "\"PREMIUM_TERM\"")
    private Integer premiumTerm;

    @Column(name = "\"RISK_START_DATE\"")
    @Temporal(TemporalType.DATE)
    private Date riskStartDate;

    @Column(name = "\"RISK_END_DATE\"")
    @Temporal(TemporalType.DATE)
    private Date riskEndDate;

    @Column(name = "\"PREMIUM_START_DATE\"")
    @Temporal(TemporalType.DATE)
    private Date premiumStartDate;

    @Column(name = "\"PREMIUM_END_DATE\"")
    @Temporal(TemporalType.DATE)
    private Date premiumEndDate;

    @Column(name = "\"MEMBER_STATUS\"")
    private String memberStatus;

    @Column(name = "\"EMPLOYEE_NO\"")
    private String employeeNo;

    @Column(name = "\"OCCUPATION_CODE\"")
    private Integer occupationCode;

    @Column(name = "\"OCCUPATION_CLASS\"")
    private String occupationClass;

    @Column(name = "\"MEMBER_CATEGORY\"")
    private String memberCategory;

    @Column(name = "\"AGE\"")
    private short age;

    @Column(name = "\"UNDECISION_FLAG\"")
    private Integer undecisionFlag;

    @Column(name = "\"UNDECISION_DESC\"")
    private String undecisionDesc;

    @Column(name = "\"EXCESS_SI\"")
    private Integer excessSI;

    @Column(name = "\"SUM_INSURED_TYPE\"")
    private String sumInsuredType;

    @Column(name = "\"LOAN_AMOUNT\"")
    private Integer loanAmount;

    @Column(name = "\"LOAN_DISBURSEMENT_DATE\"")
    @Temporal(TemporalType.DATE)
    private Date loanDisbursementDate;

    @Column(name = "\"INFORMED_PREMIUM\"")
    private Integer informedPremium;

    @Column(name = "\"TOP_UP_PREMIUM\"")
    private Integer topUpPremium;

    @Column(name = "\"LOAN_TERM\"")
    private Integer loanTerm;

    @Column(name = "\"INTEREST_RATE\"")
    private Integer interestRate;

    @Column(name = "\"MORATORIUM_FLAG\"")
    private Boolean moratoriumFlag;

    @Column(name = "\"INTEREST_PAID_DURING_MORATORIUM\"")
    private Integer interestPaidDuringMoratorium;

    @Column(name = "\"FUNDED_FLAG\"")
    private Integer fundedFlag;

    @Column(name = "\"FIRST_NAME\"")
    private String firstName;

    @Column(name = "\"LAST_NAME\"")
    private String lastName;

    @Column(name = "\"TITLE\"")
    private String title;

    @Column(name = "\"GENDER\"")
    private String gender;

    @Column(name = "\"EMAIL\"")
    private String email;

    @Column(name = "\"PHONE_NUMBER\"")
    private String phoneNumber;

    @Column(name = "\"MARITAL_STATUS\"")
    private String maritalStatus;

    @Column(name = "\"ADDRESS_LINE1\"")
    private String addressLine1;

    @Column(name = "\"ADDRESS_LINE2\"")
    private String addressLine2;

    @Column(name = "\"ADDRESS_LINE3\"")
    private String addressLine3;

    @Column(name = "\"CITY\"")
    private String city;

    @Column(name = "\"STATE\"")
    private String state;

    @Column(name = "\"PINCODE\"")
    private String pincode;

    @Column(name = "\"PREMIUM_PAYABLE\"")
    private Integer premiumPayable;

    @Column(name = "\"RELATIONSHIP_TO_CUSTOMER\"")
    private String relationshipToCustomer;

    @Column(name = "\"PERCENTAGE_ALLOCATION\"")
    private Integer percentageAllocation;

    @Column(name = "\"RELATIONSHIP_TO_NOMINEE\"")
    private String relationshipToNominee;

    @Column(name = "\"TRANSACTION_DATE\"")
    @Temporal(TemporalType.DATE)
    private Date transactionDate;

    @Column(name = "\"C_PERCENTAGE\"")
    private Byte cPercentage;

    @Column(name = "\"S_PERCENTAGE\"")
    private Byte sPercentage;

    @Column(name = "\"DEPENDENT_NUMBER\"")
    private Byte dependentNumber;

    @Column(name = "\"NATIONALITY\"")
    private String nationality;

    @Column(name = "\"CLAIMANT_ID\"")
    private UUID claimantId;

    @CreationTimestamp
    @Column(name = "\"CREATED_AT\"", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "\"UPDATED_AT\"")
    private LocalDateTime updatedAt;
}