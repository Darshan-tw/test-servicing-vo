package com.hli.services.service;

import com.hli.services.enums.*;
import com.hli.services.exceptions.DuplicateServiceRequestException;
import com.hli.services.exceptions.EntityNotFoundException;
import com.hli.services.exceptions.RequestValidationException;
import com.hli.services.repository.DocumentRepository;
import com.hli.services.repository.PlanRepository;
import com.hli.services.repository.MemberDetailsRepository;
import com.hli.services.repository.ServiceRequestsRepository;
import com.hli.services.repository.entity.DocumentEntity;
import com.hli.services.repository.entity.MemberDetailsEntity;
import com.hli.services.repository.entity.PlanEntity;
import com.hli.services.repository.entity.ServiceRequestEntity;
import com.hli.services.request.CreateFinancialSRRequestBody;
import com.hli.services.request.ModifiedFields;
import com.hli.services.response.LoanDetails;
import com.hli.services.response.MemberDetails;
import com.hli.services.response.PolicyDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.hli.services.constants.FinancialServicerequestConstants.SYSTEM_USER;
import static com.hli.services.utils.ApplicationUtils.generateUUID;
import static com.hli.services.utils.ApplicationUtils.isValidDate;

@Slf4j
@Service
public class FinancialService {


    private final PlanRepository planRepository;
    private final MemberDetailsRepository memberDetailsRepository;
    private final ServiceRequestsRepository serviceRequestRepository;
    private final DocumentRepository documentRepository;

    public FinancialService(PlanRepository planRepository, MemberDetailsRepository memberDetailsRepository, ServiceRequestsRepository serviceRequestRepository, DocumentRepository documentRepository) {
        this.planRepository = planRepository;
        this.memberDetailsRepository = memberDetailsRepository;
        this.serviceRequestRepository = serviceRequestRepository;
        this.documentRepository = documentRepository;
    }

    public PolicyDetails getPolicyDetails(String memberNo, String policyNumber) {
        PlanEntity planEntity = planRepository.findByPolicyNumber(policyNumber).orElseThrow(() -> new EntityNotFoundException(ErrorCode.PLAN_NOT_FOUND));
        MemberDetailsEntity memberDetailsEntity = memberDetailsRepository.findByPolicyNumberAndMemberNumber(policyNumber, memberNo).orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        LoanDetails loanDetails = getLoanDetails(planEntity, memberDetailsEntity);
        MemberDetails memberDetails = getMemberDetails(memberDetailsEntity);

        return PolicyDetails.builder()
                .loanDetails(loanDetails)
                .memberDetails(memberDetails)
                .build();
    }

    private LoanDetails getLoanDetails(PlanEntity planEntity, MemberDetailsEntity memberDetailsEntity) {
        BigDecimal sumAssured = BigDecimal.valueOf(memberDetailsEntity.getSumAssured());
        BigDecimal minSumAssuredMultiplicand = BigDecimal.valueOf(0.75);
        BigDecimal maxSumAssuredMultiplicand = BigDecimal.valueOf(1.25);
        LocalDate riskStartDate = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(memberDetailsEntity.getRiskStartDate()));

        return LoanDetails.builder()
                .loanType(planEntity.getLoanType())
                .lan(memberDetailsEntity.getLan())
                .policyNumber(memberDetailsEntity.getPolicyNumber())
                .planNumber(planEntity.getPlanNumber())
                .panNumber(memberDetailsEntity.getEmployeeNo())
                .originalLoanAmount(BigDecimal.valueOf(memberDetailsEntity.getLoanAmount()))
                .sumAssured(sumAssured)
                .minSumAssured(sumAssured.multiply(minSumAssuredMultiplicand))
                .maxSumAssured(sumAssured.multiply(maxSumAssuredMultiplicand))
                .minTerm(planEntity.getMinTerm())
                .maxTerm(planEntity.getMaxTerm())
                .policyTerm(memberDetailsEntity.getPremiumTerm())
                .riskCommencementDate(riskStartDate)
                .build();

    }

    private MemberDetails getMemberDetails(MemberDetailsEntity memberDetailsEntity) {
        String memberFullName = memberDetailsEntity.getFirstName().concat(" ").concat(memberDetailsEntity.getLastName());
        LocalDate dateOfBirth = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(memberDetailsEntity.getDob()));
        String completeAddress = memberDetailsEntity.getAddressLine1()
                .concat(" ").concat(memberDetailsEntity.getAddressLine2())
                .concat(" ").concat(memberDetailsEntity.getAddressLine3());

        return MemberDetails.builder()
                .memberNumber(memberDetailsEntity.getMemberNumber())
                .title(Title.valueOf(memberDetailsEntity.getTitle()))
                .name(memberFullName)
                .gender(Gender.valueOf(memberDetailsEntity.getGender()))
                .dateOfBirth(dateOfBirth)
                .address(completeAddress)
                .phoneNumber(memberDetailsEntity.getPhoneNumber())
                .email(memberDetailsEntity.getEmail())
                .build();
    }

    public void createFinancialServiceRequest(CreateFinancialSRRequestBody request, List<MultipartFile> uploadedDocuments) {
        memberDetailsRepository.findByPolicyNumberAndMemberNumber(request.getPolicyNumber(), request.getMemberNumber()).orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        if (request.getModifiedFields().size() == 1) {
            String serviceRequestType = request.getModifiedFields().getFirst().getServiceRequestType().name();
            if (isDuplicateServiceRequest(request, serviceRequestType)) {
                throw new DuplicateServiceRequestException(String.format("A pending service request already exists for the given policy number, member number for service request type: %s ", serviceRequestType));
            }
            createNewServiceRequest(request, request.getModifiedFields().getFirst());
        }
        else {
            for (ModifiedFields modifiedField : request.getModifiedFields()) {
                createNewServiceRequest(request, modifiedField);
            }
        }

        for (MultipartFile document  : uploadedDocuments) {
            saveDocument(request, document);
        }
    }

    private void saveDocument(CreateFinancialSRRequestBody request, MultipartFile document) {
        try {
            DocumentEntity doc = new DocumentEntity();
            doc.setDocumentName(document.getOriginalFilename());
            doc.setFileSize(String.valueOf(document.getSize()));
            doc.setDocumentId(generateUUID());
            doc.setMemberId(request.getMemberNumber());
            doc.setClaimId(request.getPolicyNumber());
            doc.setUploadedBy(SYSTEM_USER);
            doc.setCreatedBy(SYSTEM_USER);
            doc.setOcrAvailable(false);
            doc.setCreatedAt(new Date(System.currentTimeMillis()));

            documentRepository.save(doc);

            log.info("[FinancialService::createFinancialServiceRequest] Saved uploaded document: {}", document.getOriginalFilename());
        } catch (Exception e) {
            log.error("[FinancialService::createFinancialServiceRequest] Error saving uploaded document: {}", document.getOriginalFilename(), e);
        }
    }

    private boolean isDuplicateServiceRequest(CreateFinancialSRRequestBody request, String serviceRequestType) {
        return serviceRequestRepository.findByPolicyNumberAndMemberNumberAndServiceRequestTypeAndServiceRequestStatus(
                request.getPolicyNumber(), request.getMemberNumber(), serviceRequestType, ServiceRequestStatus.PENDING.name()).isPresent();
    }

    private void createNewServiceRequest(CreateFinancialSRRequestBody request, ModifiedFields modifiedField) {
        String serviceRequestType = modifiedField.getServiceRequestType().name();

        if (isDuplicateServiceRequest(request, serviceRequestType)) {
            return;
        }

        validateModifiedFieldAndValue(modifiedField, request.getPolicyNumber(), request.getMemberNumber());

        ServiceRequestEntity serviceRequestEntity = new ServiceRequestEntity();
        serviceRequestEntity.setServiceRequestId(generateUUID());
        serviceRequestEntity.setPolicyNumber(request.getPolicyNumber());
        serviceRequestEntity.setMemberNumber(request.getMemberNumber());
        serviceRequestEntity.setServiceRequestType(serviceRequestType);
        serviceRequestEntity.setServiceRequestValue(modifiedField.getNewValue());
        serviceRequestEntity.setServiceRequestStatus(ServiceRequestStatus.PENDING.name());
        serviceRequestEntity.setServiceRequestDate(new Date(System.currentTimeMillis()));
        serviceRequestEntity.setServiceRequestCreatedBy(SYSTEM_USER);
        serviceRequestEntity.setServiceRequestUpdatedBy(SYSTEM_USER);
        serviceRequestEntity.setServiceRequestCreatedDate(new Date(System.currentTimeMillis()));
        serviceRequestEntity.setServiceRequestUpdatedDate(new Date(System.currentTimeMillis()));

        serviceRequestRepository.save(serviceRequestEntity);
        log.info("[FinancialService::createFinancialServiceRequest] Processing field: {} with new value: {}", modifiedField.getServiceRequestType(), modifiedField.getNewValue());

    }

    private void validateModifiedFieldAndValue(ModifiedFields modifiedField, String policyNumber, String memberNumber) {
        ServiceRequestType type = modifiedField.getServiceRequestType();
        if (type == null) {
            throw new RequestValidationException(ErrorCode.INVALID_REQUEST, "Service request type cannot be null");
        }
        String newValue = modifiedField.getNewValue();
        if (newValue == null) {
            throw new RequestValidationException(ErrorCode.INVALID_REQUEST, "New value cannot be null");
        }
        switch (type) {
            case GENDER:
                if (!Arrays.asList("Male", "Female", "Transgender").contains(newValue)) {
                    throw new RequestValidationException(ErrorCode.INVALID_REQUEST, "Invalid gender value: " + newValue);
                }
                break;
            case TITLE:
                if (!Arrays.asList("Mr", "Mrs", "Ms").contains(newValue)) {
                    throw new RequestValidationException(ErrorCode.INVALID_REQUEST, "Invalid title value: " + newValue);
                }
                break;
            case DATE_OF_BIRTH:
            case RISK_COMMENCEMENT_DATE:
                if (!isValidDate(newValue)) {
                    throw new RequestValidationException(ErrorCode.INVALID_REQUEST, "Invalid date format for " + type + ": " + newValue);
                }
                break;
            case SUM_ASSURED:
                BigDecimal sumAssured = new BigDecimal(newValue);
                BigDecimal originalLoanAmount = getOriginalLoanAmount(policyNumber, memberNumber);
                BigDecimal minSumAssured = originalLoanAmount.multiply(BigDecimal.valueOf(0.75));
                BigDecimal maxSumAssured = originalLoanAmount.multiply(BigDecimal.valueOf(1.25));
                if (sumAssured.compareTo(minSumAssured) < 0 || sumAssured.compareTo(maxSumAssured) > 0) {
                    throw new RequestValidationException(ErrorCode.INVALID_REQUEST, "Sum assured must be between " + minSumAssured + " and " + maxSumAssured);
                }
                break;
            case POLICY_TERM:
                int term = Integer.parseInt(newValue);
                if (term < 5 || term > 25) {
                    throw new RequestValidationException(ErrorCode.INVALID_REQUEST, "Term must be between 5 and 25");
                }
                break;
            default:
                throw new RequestValidationException(ErrorCode.INVALID_REQUEST, "Unsupported service request type: " + type);
        }
    }

    private BigDecimal getOriginalLoanAmount(String policyNumber, String memberNumber) {
        MemberDetailsEntity memberDetailsEntity = memberDetailsRepository.findByPolicyNumberAndMemberNumber(policyNumber, memberNumber).orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND));
        return BigDecimal.valueOf(memberDetailsEntity.getLoanAmount());
    }


}