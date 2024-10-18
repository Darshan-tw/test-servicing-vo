package com.hli.services.exceptions;

import com.hli.services.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class EntityNotFoundException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;

    public EntityNotFoundException(ErrorCode errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorMessage;
    }

    public EntityNotFoundException(String errorMessage) {
        super(errorMessage);
        this.errorCode = HttpStatus.NOT_FOUND.toString();
        this.errorMessage = errorMessage;
    }

    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMessage();
    }
}
