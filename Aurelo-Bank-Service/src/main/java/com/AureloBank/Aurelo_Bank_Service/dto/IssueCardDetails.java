package com.AureloBank.Aurelo_Bank_Service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IssueCardDetails {

    private String status;
    private String cardNumber;
    private String expiryDate;
    private String cvv;
    private String cardNetwork;
    private LocalDateTime issuedAt;
}

