package com.hli.services.exceptions.handlers;

import com.fasterxml.jackson.core.JsonParseException;
import com.hli.services.exceptions.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class FinancialServicesExceptionHandlers {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {

         log.error("EntityNotFoundException: ", ex);
        return new ResponseEntity<>("An unexpected error occurred: [" +  ex.getErrorCode()  + "] " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<String> handleJsonParseException(JsonParseException ex) {

         log.error("JsonParseException: ", ex);
        return new ResponseEntity<>("Unable to parse request: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {

        log.error("RuntimeException: ", ex);
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}