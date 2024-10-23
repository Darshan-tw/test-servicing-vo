package com.hli.services.exceptions;

import com.hli.services.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class EntityNotFoundException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;
    private final String details;

    public EntityNotFoundException(ErrorCode errorCode, String errorMessage, String details) {
        super(errorMessage);
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorMessage;
        this.details = errorCode.getMessage();
    }

    public EntityNotFoundException(String details) {
        super(ErrorCode.MEMBER_NOT_FOUND.getMessage());
        this.errorCode = ErrorCode.ENTITY_NOT_FOUND.getCode();
        this.errorMessage = ErrorCode.MEMBER_NOT_FOUND.getMessage();
        this.details = details;
    }

    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMessage();
        this.details = null;
    }
}
