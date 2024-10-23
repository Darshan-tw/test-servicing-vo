package com.hli.services.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum ServiceRequestType {
    @JsonProperty("gender")
    GENDER("gender"),

    @JsonProperty("sumAssured")
    SUM_ASSURED("sumAssured"),

    @JsonProperty("title")
    TITLE("title"),

    @JsonProperty("dateOfBirth")
    DATE_OF_BIRTH("dateOfBirth"),

    @JsonProperty("policyTerm")
    POLICY_TERM("policyTerm"),

    @JsonProperty("riskCommencementDate")
    RISK_COMMENCEMENT_DATE("riskCommencementDate");

    private final String value;

    ServiceRequestType(String value) {
        this.value = value;
    }

    public static List<ServiceRequestType> getValuesList() {
        return Arrays.asList(ServiceRequestType.values());
    }

    public static boolean isValidValue(String value) {
        return Arrays.stream(ServiceRequestType.values())
                     .anyMatch(type -> type.getValue().equals(value));
    }
}