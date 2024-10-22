package com.hli.services.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public enum ServiceRequestType {
    @JsonProperty("gender")
    GENDER,

    @JsonProperty("sumAssured")
    SUM_ASSURED,

    @JsonProperty("title")
    TITLE,

    @JsonProperty("dateOfBirth")
    DATE_OF_BIRTH,

    @JsonProperty("policyTerm")
    POLICY_TERM,

    @JsonProperty("riskCommencementDate")
    RISK_COMMENCEMENT_DATE

}