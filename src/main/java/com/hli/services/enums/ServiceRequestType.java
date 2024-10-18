package com.hli.services.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public enum ServiceRequestType {
    @JsonProperty("Gender")
    GENDER,

    @JsonProperty("SumAssured")
    SUM_ASSURED,

    @JsonProperty("Title")
    TITLE,

    @JsonProperty("DateOfBirth")
    DATE_OF_BIRTH,

    @JsonProperty("PolicyTerm")
    POLICY_TERM,

    @JsonProperty("RiskCommencementDate")
    RISK_COMMENCEMENT_DATE

}