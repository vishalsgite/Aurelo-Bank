package com.AureloBank.Aurelo_Bank_Service.dto;

import lombok.Data;

@Data
public class CardNetworkIssueResponse {

    private String responseCode;
    private String responseMessage;
    private IssueCardDetails issueCardDetails;
}
