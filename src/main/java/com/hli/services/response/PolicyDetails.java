package com.hli.services.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicyDetails {
    private LoanDetails loanDetails;
    private MemberDetails memberDetails;
}