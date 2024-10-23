package com.hli.services.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {
    MEMBER_NOT_FOUND("MEMBER_NOT_FOUND", "Member not found"),
    ENTITY_NOT_FOUND("ENTITY_NOT_FOUND", "Entity not found"),
    LOAN_NOT_FOUND("LOAN_NOT_FOUND", "Loan not found"),
    POLICY_NOT_FOUND("POLICY_NOT_FOUND", "Policy not found"),
    PLAN_NOT_FOUND("PLAN_NOT_FOUND", "Plan not found"),
    INVALID_REQUEST("INVALID_REQUEST", "Invalid request"),
    CLAIM_NOT_FOUND("CLAIM_NOT_FOUND", "Claim not found"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "Internal server error"),
    DUPLICATE_SERVICE_REQUEST("DUPLICATE_REQUEST", "Duplicate Service request");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
