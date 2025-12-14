package com.AureloBank.Aurelo_Bank_Service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditDebitRequest {
    private String tracerId;
    private String accountNumber;
    private BigDecimal amount;

}
