package com.AureloBank.Aurelo_Bank_Service.controller;


import com.AureloBank.Aurelo_Bank_Service.dto.BankResponse;
import com.AureloBank.Aurelo_Bank_Service.dto.CreditDebitRequest;
import com.AureloBank.Aurelo_Bank_Service.dto.GetUserInfoRequest;
import com.AureloBank.Aurelo_Bank_Service.dto.UserRequest;
import com.AureloBank.Aurelo_Bank_Service.service.UserService;
import com.AureloBank.Aurelo_Bank_Service.util.AccountUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Slf4j
@Tag(name = "User Management Endpoints")
public class UserController {

    @Autowired
    UserService userService;

    @Operation(summary = "Create New User Account",description = "Creating a new user and assigning an account ID")
    @ApiResponse(responseCode = "200",description = "Http Status 201 Created")
    @PostMapping("/createBankAccount")
    public BankResponse createBankAccount(@RequestBody UserRequest userRequest){

       log.info("{}{} REQUEST RECEIVED TO CREATE ACCOUNT FOR EMAIL: {}", AccountUtils.ABS_SERVICE,userRequest.getTracerId(),userRequest.getEmail());

        return userService.createBankAccount(userRequest);
    }

    @PostMapping("/getUserInfo")
    public BankResponse getUserInfo(@RequestBody GetUserInfoRequest request) {

        log.info("{}{} REQUEST RECEIVED TO FETCH USER INFO FOR ACCOUNT : {}",AccountUtils.ABS_SERVICE,request.getTracerId(),request.getAccountNumber());

        return userService.getUserInfo(request);
    }

    @PostMapping("/creditUserBalance")
    public BankResponse creditUserBalance(@RequestBody CreditDebitRequest request) {

        log.info("{}{} REQUEST RECEIVED TO UPDATE USER BALANACE FOR ACCOUNT : {}",AccountUtils.ABS_SERVICE,request.getTracerId(),request.getAccountNumber());

        return userService.creditAccount(request);
    }

    @PostMapping("/debitUserBalance")
    public BankResponse debitUserBalance(@RequestBody CreditDebitRequest request) {

        log.info("{}{} REQUEST RECEIVED TO DEBIT USER BALANACE FOR ACCOUNT : {}",AccountUtils.ABS_SERVICE,request.getTracerId(),request.getAccountNumber());

        return userService.debitAccount(request);
    }
}
