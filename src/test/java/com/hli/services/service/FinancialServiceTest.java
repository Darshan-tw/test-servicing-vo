package com.hli.services.service;

import com.hli.services.enums.Gender;
import com.hli.services.enums.ServiceRequestType;
import com.hli.services.enums.Title;
import com.hli.services.exceptions.EntityNotFoundException;
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
import com.hli.services.response.PolicyDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FinancialServiceTest {

    @Mock
    private PlanRepository planRepository;

    @Mock
    private MemberDetailsRepository memberDetailsRepository;

    @Mock
    private ServiceRequestsRepository serviceRequestRepository;

    @Mock
    private DocumentRepository documentRepository;

    @InjectMocks
    private FinancialService financialService;

   @BeforeEach
    public void setup() {
        financialService = new FinancialService(planRepository, memberDetailsRepository, serviceRequestRepository, documentRepository);
    }

    @Test
    public void shouldReturnPolicyDetails_whenPolicyDetailsAreAvailable() {
        String memberNo = "123";
        String policyNumber = "456";
        int sumInsured = 100000;
        int loanTerm = 10;
        BigDecimal originalLoanAmount = BigDecimal.valueOf(sumInsured);

        int minTerm = 5;
        int maxTerm = 20;
        PlanEntity planEntity = PlanEntity.builder()
                .loanType("expectedLoanType")
                .planNumber("PLN123")
                .minTerm(minTerm)
                .maxTerm(maxTerm)
                .build();
        Date dob = Date.valueOf("1990-01-01");
        MemberDetailsEntity memberDetailsEntity = MemberDetailsEntity.builder()
                .firstName("firstName")
                .lastName("LastName")
                .title(Title.Mr.toString())
                .gender(Gender.Male.toString())
                .policyNumber(policyNumber)
                .memberNumber(memberNo)
                .dob(dob)
                .employeeNo("panNumber")
                .sumAssured(sumInsured)
                .loanTerm(loanTerm)
                .premiumTerm(loanTerm)
                .loanAmount(sumInsured)
                .riskStartDate(Date.valueOf("2023-01-01"))
                .phoneNumber("7999999999")
                .email("name@email.com")
                .addressLine1("home123")
                .addressLine2("street ABC")
                .addressLine3("near bakery")
                .build();

        BigDecimal expectedMinSumAssured = BigDecimal.valueOf(sumInsured).multiply(BigDecimal.valueOf(0.75));
        BigDecimal expectedMaxSumAssured = BigDecimal.valueOf(sumInsured).multiply(BigDecimal.valueOf(1.25));
        String expectedName = memberDetailsEntity.getFirstName() + " " + memberDetailsEntity.getLastName();
        String expectedAddress = memberDetailsEntity.getAddressLine1() + " " + memberDetailsEntity.getAddressLine2() + " " + memberDetailsEntity.getAddressLine3();
        LocalDate expectedDateOfBirth = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(memberDetailsEntity.getDob()));

        LocalDate expectedRiskStartDate =  LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(memberDetailsEntity.getRiskStartDate()));

        when(planRepository.findByPolicyNumber(policyNumber)).thenReturn(Optional.of(planEntity));
        when(memberDetailsRepository.findByPolicyNumberAndMemberNumber(policyNumber, memberNo)).thenReturn(Optional.of(memberDetailsEntity));

        PolicyDetails policyDetails = financialService.getPolicyDetails(memberNo, policyNumber);

        assertEquals(planEntity.getLoanType(), policyDetails.getLoanDetails().getLoanType());
        assertEquals(memberDetailsEntity.getLan(), policyDetails.getLoanDetails().getLan());
        assertEquals(planEntity.getPlanNumber(), policyDetails.getLoanDetails().getPlanNumber());
        assertEquals(memberDetailsEntity.getEmployeeNo(), policyDetails.getLoanDetails().getPanNumber());
        assertEquals(originalLoanAmount, policyDetails.getLoanDetails().getOriginalLoanAmount());
        assertEquals(BigDecimal.valueOf(memberDetailsEntity.getSumAssured()), policyDetails.getLoanDetails().getSumAssured());
        assertEquals(expectedMinSumAssured, policyDetails.getLoanDetails().getMinSumAssured());
        assertEquals(expectedMaxSumAssured, policyDetails.getLoanDetails().getMaxSumAssured());
        assertEquals(5, policyDetails.getLoanDetails().getMinTerm());
        assertEquals(20, policyDetails.getLoanDetails().getMaxTerm());
        assertEquals(memberDetailsEntity.getPremiumTerm(), policyDetails.getLoanDetails().getPremiumTerm());
        assertEquals(expectedRiskStartDate, policyDetails.getLoanDetails().getRiskStartDate());
        assertEquals(memberDetailsEntity.getMemberNumber(), policyDetails.getMemberDetails().getMemberNumber());
        assertEquals(Title.Mr, policyDetails.getMemberDetails().getTitle());
        assertEquals(expectedName, policyDetails.getMemberDetails().getName());
        assertEquals(Gender.Male, policyDetails.getMemberDetails().getGender());
        assertEquals(expectedDateOfBirth, policyDetails.getMemberDetails().getDateOfBirth());
        assertEquals(expectedAddress, policyDetails.getMemberDetails().getAddress());
        assertEquals(memberDetailsEntity.getPhoneNumber(), policyDetails.getMemberDetails().getPhoneNumber());
        assertEquals(memberDetailsEntity.getEmail(), policyDetails.getMemberDetails().getEmail());
    }

    @Test
    public void shouldThrowEntityNotFoundException_whenPlanNotFound() {
        String memberNo = "123";
        String policyNumber = "456";

        when(planRepository.findByPolicyNumber(policyNumber)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> financialService.getPolicyDetails(memberNo, policyNumber));
    }

    @Test
    public void shouldThrowEntityNotFoundException_whenMemberNotFound() {
        String memberNo = "123";
        String policyNumber = "456";
        PlanEntity planEntity = new PlanEntity();

        when(planRepository.findByPolicyNumber(policyNumber)).thenReturn(Optional.of(planEntity));
        when(memberDetailsRepository.findByPolicyNumberAndMemberNumber(policyNumber, memberNo)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> financialService.getPolicyDetails(memberNo, policyNumber));
    }


    @Test
    public void shouldSaveServiceRequest_whenValidFinancialServiceRequestIsPresent() {
        String memberNo = "123";
        String policyNumber = "456";
        CreateFinancialSRRequestBody request = new CreateFinancialSRRequestBody();
        request.setPolicyNumber(policyNumber);
        request.setMemberNumber(memberNo);
        ModifiedFields modifiedField = new ModifiedFields();
        modifiedField.setServiceRequestType(ServiceRequestType.GENDER);
        modifiedField.setNewValue(Gender.Male.toString());
        request.setModifiedFields(Collections.singletonList(modifiedField));
        MultipartFile mockFile = mock(MultipartFile.class);
        request.setUploadedDocuments(Collections.singletonList(mockFile));
        MemberDetailsEntity memberDetailsEntity = MemberDetailsEntity.builder()
                .firstName("firstName")
                .lastName("LastName")
                .title(Title.Mr.toString())
                .gender(Gender.Male.toString())
                .policyNumber(policyNumber)
                .memberNumber(memberNo)
                .dob(Date.valueOf("1990-01-01"))
                .employeeNo("panNumber")
                .sumAssured(1000000)
                .loanTerm(15)
                .premiumTerm(10)
                .loanAmount(1000000)
                .riskStartDate(Date.valueOf("2023-01-01"))
                .phoneNumber("7999999999")
                .email("name@email.com")
                .addressLine1("home123")
                .addressLine2("street ABC")
                .addressLine3("near bakery")
                .build();

        when(mockFile.getOriginalFilename()).thenReturn("test.txt");
        when(mockFile.getSize()).thenReturn(100L);
        when(memberDetailsRepository.findByPolicyNumberAndMemberNumber(policyNumber, memberNo)).thenReturn(Optional.of(memberDetailsEntity));
        financialService.createFinancialServiceRequest(request);

        verify(serviceRequestRepository, times(1)).save(any(ServiceRequestEntity.class));
        verify(documentRepository, times(1)).save(any(DocumentEntity.class));
    }

    @Test
    public void shouldThrowMemberNotFoundException_whenPolicyIsNotPresent() {
        String memberNo = "123";
        String policyNumber = "456";
        CreateFinancialSRRequestBody request = new CreateFinancialSRRequestBody();
        request.setPolicyNumber(policyNumber);
        request.setMemberNumber(memberNo);
        when(memberDetailsRepository.findByPolicyNumberAndMemberNumber(policyNumber, memberNo)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> financialService.createFinancialServiceRequest(request));
    }

}