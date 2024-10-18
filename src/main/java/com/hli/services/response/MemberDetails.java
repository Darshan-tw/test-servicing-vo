package com.hli.services.response;

import com.hli.services.enums.Gender;
import com.hli.services.enums.Title;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDetails {
    private String memberNumber;
    private Title title;
    private String name;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String address;
    private String phoneNumber;
    private String email;
}