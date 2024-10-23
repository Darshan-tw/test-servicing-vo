package com.hli.services.validation;

import com.hli.services.enums.ServiceRequestType;
import com.hli.services.exceptions.RequestValidationException;
import com.hli.services.enums.ErrorCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


import java.util.Arrays;

import static com.hli.services.enums.ServiceRequestType.isValidValue;

public class ServiceRequestTypeValidator implements ConstraintValidator<ValidServiceRequestType, ServiceRequestType> {

 @Override
public boolean isValid(ServiceRequestType type, ConstraintValidatorContext context) {
    if (type == null || !ServiceRequestType.isValidValue(type.getValue())) {
        throw new RequestValidationException(ErrorCode.INVALID_REQUEST, "Invalid service request type: " + (type != null ? type.getValue() : "null"));
    }
    return true;
}
}