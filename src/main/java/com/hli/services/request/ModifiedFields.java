package com.hli.services.request;

import com.hli.services.enums.ServiceRequestType;
import com.hli.services.validation.ValidServiceRequestType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Data
@Validated
public class ModifiedFields {

    @ValidServiceRequestType
    private ServiceRequestType serviceRequestType;
    private String newValue;

}