package com.AureloBank.Aurelo_Bank_Service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankResponse {
    private String responseCode;
    private String responseMessage;
    private AccountInfo accountInfo;

    @Override
    public String toString() {
        return "BANK_RESPONSE{" +
                "RESPONSE_CODE='" + responseCode + '\'' +
                ", RESPONSE_MESSAGE='" + responseMessage + '\'' +
                ", ACCOUNT_INFO=" + (accountInfo != null ? accountInfo.toString() : "NULL") +
                '}';
    }

}
