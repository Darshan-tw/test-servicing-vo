package com.hli.services.exceptions;

import com.hli.services.enums.ErrorCode;
import lombok.Getter;

@Getter
public class DuplicateServiceRequestException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;
    private final String details;

    public DuplicateServiceRequestException(ErrorCode errorCode, String details) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMessage();
        this.details = details;
    }

    public DuplicateServiceRequestException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMessage();
        this.details = null;
    }

    public DuplicateServiceRequestException(String details) {
        super(ErrorCode.DUPLICATE_SERVICE_REQUEST.getMessage());
        this.errorCode = ErrorCode.DUPLICATE_SERVICE_REQUEST.getCode();
        this.errorMessage = ErrorCode.DUPLICATE_SERVICE_REQUEST.getMessage();
        this.details = details;
    }
}