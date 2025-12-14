package com.AureloBank.Aurelo_Bank_Service.service;


import com.AureloBank.Aurelo_Bank_Service.dto.CardIssueRequest;
import com.AureloBank.Aurelo_Bank_Service.dto.CardIssueResponse;
import com.AureloBank.Aurelo_Bank_Service.dto.CardNetworkIssueResponse;


public interface BankCardService {
    CardNetworkIssueResponse issueDebitCard(CardIssueRequest request);
}
