package com.AureloBank.Aurelo_Bank_Service.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountInfo {

    @Schema(description = "User Customer Id")
    private String customerId;
    @Schema(description = "User Account Name")
    private String accountName;
    @Schema(description = "User Account Balance")
    private BigDecimal accountBalance;
    @Schema(description = "User Account Number")
    private String accountNumber;
    @Schema(description = "User Debit Card Number")
    private String debitCardNumber;

    @Override
    public String toString() {
        return "ACCOUNT_INFO{" +
                "CUSTOMER_ID='" + customerId + '\'' +
                ", ACCOUNT_NAME='" + accountName + '\'' +
                ", ACCOUNT_BALANCE=" + accountBalance +
                ", ACCOUNT_NUMBER='****" +
                (accountNumber != null && accountNumber.length() > 4
                        ? accountNumber.substring(accountNumber.length() - 4)
                        : "NA") + '\'' +
                '}';
    }

}
