package com.hli.services.exceptions;

import com.hli.services.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DuplicateServiceRequest extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;

    public DuplicateServiceRequest(ErrorCode errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorMessage;
    }

    public DuplicateServiceRequest(String errorMessage) {
        super(errorMessage);
        this.errorCode = HttpStatus.NOT_FOUND.toString();
        this.errorMessage = errorMessage;
    }

    public DuplicateServiceRequest(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMessage();
    }
}
