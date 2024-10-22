package com.hli.services.controller;

import com.hli.services.request.CreateFinancialSRRequestBody;
import com.hli.services.request.ModifiedFields;
import com.hli.services.response.PolicyDetails;
import com.hli.services.service.FinancialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FinancialServiceControllerTest {

@Mock
private FinancialService financialService;

@InjectMocks
private FinancialServiceController financialServiceController;

@BeforeEach
public void setUp() {
    financialServiceController = new FinancialServiceController(financialService);
}

    @Test
    public void testGetPolicyDetails() {
        String memberNo = "123";
        String policyNumber = "456";
        PolicyDetails policyDetails = new PolicyDetails();

        when(financialService.getPolicyDetails(anyString(), anyString())).thenReturn(policyDetails);

        ResponseEntity<PolicyDetails> response = financialServiceController.getPolicyDetails(memberNo, policyNumber);

        assertEquals(ResponseEntity.ok(policyDetails), response);
    }

    @Test
    public void testCreateFinancialServiceRequest() {
        CreateFinancialSRRequestBody request = new CreateFinancialSRRequestBody();
        request.setPolicyNumber("POL001");
        request.setMemberNumber("MEM001");
        request.setModifiedFields(Collections.singletonList(new ModifiedFields()));
        List<MultipartFile> uploadedDocuments = Collections.singletonList(mock(MultipartFile.class));

        doNothing().when(financialService).createFinancialServiceRequest(request, uploadedDocuments);

        ResponseEntity<Void> response = financialServiceController.createFinancialServiceRequest(request, uploadedDocuments);

        verify(financialService, times(1)).createFinancialServiceRequest(request, uploadedDocuments);
    }
}