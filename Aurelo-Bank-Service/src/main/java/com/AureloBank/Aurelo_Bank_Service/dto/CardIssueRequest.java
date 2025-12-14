package com.AureloBank.Aurelo_Bank_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardIssueRequest {
    private String tracerId;
    private String bankCode;
    private String accountName;
    private String accountNumber;
    private String cardType;

    @Override
    public String toString() {
        return "CARD_ISSUE_REQUEST{" +
                "TRACER_ID='" + tracerId + '\'' +
                ", BANK_CODE='" + bankCode + '\'' +
                ", ACCOUNT_NAME='" + accountName + '\'' +
                ", ACCOUNT_NUMBER='****" +
                (accountNumber != null && accountNumber.length() > 4
                        ? accountNumber.substring(accountNumber.length() - 4)
                        : "NA") + '\'' +
                ", CARD_TYPE='" + cardType + '\'' +
                '}';
    }

}
