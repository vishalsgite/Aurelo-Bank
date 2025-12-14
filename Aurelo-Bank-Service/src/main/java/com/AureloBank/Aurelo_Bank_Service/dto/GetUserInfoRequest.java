package com.AureloBank.Aurelo_Bank_Service.dto;

import lombok.Data;

@Data
public class GetUserInfoRequest {
    private String tracerId;
    private String phoneNumber;
    private String accountNumber;
}
