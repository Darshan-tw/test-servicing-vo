package com.hli.services.exceptions;

import com.hli.services.enums.ErrorCode;
import lombok.Getter;

@Getter
public class RequestValidationException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;
    private final String details;

    public RequestValidationException(ErrorCode errorCode, String details) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMessage();
        this.details = details;
    }

    public RequestValidationException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMessage();
        this.details = null;
    }

    public RequestValidationException(String details) {
        super(ErrorCode.INVALID_REQUEST.getMessage());
        this.errorCode = ErrorCode.INVALID_REQUEST.getCode();
        this.errorMessage = ErrorCode.INVALID_REQUEST.getMessage();
        this.details = details;
    }
}