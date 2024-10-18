package com.hli.services.validation;

import com.hli.services.enums.ServiceRequestType;
import com.hli.services.exceptions.RequestValidationException;
import com.hli.services.enums.ErrorCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


import java.util.Arrays;

public class ServiceRequestTypeValidator implements ConstraintValidator<ValidServiceRequestType, ServiceRequestType> {

    @Override
    public boolean isValid(ServiceRequestType value, ConstraintValidatorContext context) {
        if (value == null || !Arrays.asList(ServiceRequestType.values()).contains(value)) {
            throw new RequestValidationException(ErrorCode.INVALID_REQUEST, "Invalid service request type: " + value);
        }
        return true;
    }
}