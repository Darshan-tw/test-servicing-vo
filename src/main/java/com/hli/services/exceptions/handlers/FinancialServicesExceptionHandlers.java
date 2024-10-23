package com.hli.services.exceptions.handlers;

import com.fasterxml.jackson.core.JsonParseException;
import com.hli.services.exceptions.DuplicateServiceRequestException;
import com.hli.services.exceptions.EntityNotFoundException;
import com.hli.services.exceptions.RequestValidationException;
import com.hli.services.response.ErrorResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
public class FinancialServicesExceptionHandlers {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseEntity> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorResponseEntity errorResponse = ErrorResponseEntity.builder()
                .status(BAD_REQUEST.getReasonPhrase())
                .statusCode(BAD_REQUEST.value())
                .error(new ErrorResponseEntity.ErrorDetails(ex.getErrorCode(), ex.getMessage(), ex.getDetails(), LocalDateTime.now(), null))
                .build();

        log.error("EntityNotFoundException: ", ex);
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateServiceRequestException.class)
    public ResponseEntity<ErrorResponseEntity> handleDuplicateServiceRequestException(DuplicateServiceRequestException ex) {
        ErrorResponseEntity errorResponse = ErrorResponseEntity.builder()
                .status(BAD_REQUEST.getReasonPhrase())
                .statusCode(BAD_REQUEST.value())
                .error(new ErrorResponseEntity.ErrorDetails(ex.getErrorCode(), ex.getMessage(), ex.getDetails(), LocalDateTime.now(), null))
                .build();

        log.error("DuplicateServiceRequestException: ", ex);
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }

    @ExceptionHandler(RequestValidationException.class)
    public ResponseEntity<ErrorResponseEntity> handleRequestValidationException(RequestValidationException ex) {
        ErrorResponseEntity errorResponse = ErrorResponseEntity.builder()
                .status(BAD_REQUEST.getReasonPhrase())
                .statusCode(BAD_REQUEST.value())
                .error(new ErrorResponseEntity.ErrorDetails(ex.getErrorCode(), ex.getMessage(), ex.getDetails(), LocalDateTime.now(), null))
                .build();

        log.error("RequestValidationException: ", ex);
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<ErrorResponseEntity> handleJsonParseException(JsonParseException ex) {

        ErrorResponseEntity errorResponse = ErrorResponseEntity.builder()
                .status(BAD_REQUEST.getReasonPhrase())
                .statusCode(BAD_REQUEST.value())
                .error(new ErrorResponseEntity.ErrorDetails(BAD_REQUEST.getReasonPhrase(), ex.getMessage(), null, LocalDateTime.now(), null))
                .build();

        log.error("JsonParseException: ", ex);
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseEntity> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {

        ErrorResponseEntity errorResponse = ErrorResponseEntity.builder()
                .status(BAD_REQUEST.getReasonPhrase())
                .statusCode(BAD_REQUEST.value())
                .error(new ErrorResponseEntity.ErrorDetails(BAD_REQUEST.getReasonPhrase(), ex.getMessage(), null, LocalDateTime.now(), null))
                .build();

        log.error("HttpMessageNotReadableException: ", ex);
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseEntity> handleRuntimeException(RuntimeException ex) {
        ErrorResponseEntity errorResponse = ErrorResponseEntity.builder()
                .status(INTERNAL_SERVER_ERROR.getReasonPhrase())
                .statusCode(INTERNAL_SERVER_ERROR.value())
                .error(new ErrorResponseEntity.ErrorDetails(INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage(), null, LocalDateTime.now(), null))
                .build();


        log.error("RuntimeException: ", ex);
        return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
    }
}