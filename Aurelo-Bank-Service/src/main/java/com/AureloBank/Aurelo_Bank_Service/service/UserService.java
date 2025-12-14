package com.AureloBank.Aurelo_Bank_Service.service;

import com.AureloBank.Aurelo_Bank_Service.dto.BankResponse;
import com.AureloBank.Aurelo_Bank_Service.dto.CreditDebitRequest;
import com.AureloBank.Aurelo_Bank_Service.dto.GetUserInfoRequest;
import com.AureloBank.Aurelo_Bank_Service.dto.UserRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    BankResponse createBankAccount(UserRequest userRequest);
    BankResponse getUserInfo(GetUserInfoRequest request);
    BankResponse creditAccount(CreditDebitRequest request);
    BankResponse debitAccount(CreditDebitRequest request);
}
