package com.hli.services.controller;

import com.hli.services.request.CreateFinancialSRRequestBody;
import com.hli.services.response.PolicyDetails;
import com.hli.services.service.FinancialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.hli.services.constants.FinancialServicerequestConstants.*;

@RestController
@RequestMapping(BASE_PATH)
public class FinancialServiceController {

    private final FinancialService financialService;

    public FinancialServiceController(FinancialService financialService) {
        this.financialService = financialService;
    }

    @GetMapping(value = GET_POLICY_DETAILS, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PolicyDetails> getPolicyDetails(@RequestParam String policyNumber, @RequestParam String memberNo) {
        return ResponseEntity.ok(financialService.getPolicyDetails(memberNo, policyNumber));
    }

    @PostMapping(value = CREATE_SERVICE_REQUEST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> createFinancialServiceRequest(@RequestBody CreateFinancialSRRequestBody request) {
        financialService.createFinancialServiceRequest(request);
        return ResponseEntity.ok().build();
    }
}