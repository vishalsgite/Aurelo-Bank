package com.AureloBank.Aurelo_Bank_Service.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private Long tracerId;
    private String firstName;
    private String lastName;
    private String otherName;
    private String gender;
    private String address;
    private String stateOfOrigin;
    private String email;
    private String phoneNumber;
    private String alternativeNumber;

    @Override
    public String toString() {
        return "UserRequest{tracerId=" + tracerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", otherName='" + otherName + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", stateOfOrigin='" + stateOfOrigin + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", alternativeNumber='" + alternativeNumber + '\'' +
                '}';
    }

}
