package com.AureloBank.Aurelo_Bank_Service.service;

import com.AureloBank.Aurelo_Bank_Service.dto.*;
import com.AureloBank.Aurelo_Bank_Service.entity.User;
import com.AureloBank.Aurelo_Bank_Service.repository.UserRepository;
import com.AureloBank.Aurelo_Bank_Service.util.AccountUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public BankResponse createBankAccount(UserRequest userRequest) {

        log.info("{} {} STARTING CREATE ACCOUNT PROCESS FOR : {}",AccountUtils.ABS_SERVICE,userRequest.getTracerId(),userRequest);

        try {

            log.info("{}{} CHECKING IF EMAIL ALREADY EXISTS IN DATABASE  {}",AccountUtils.ABS_SERVICE,userRequest.getTracerId(),userRequest);
            if (userRepository.existsByEmail(userRequest.getEmail())) {
                log.warn("{}{} ACCOUNT ALREADY EXISTS FOR EMAIL: {}",AccountUtils.ABS_SERVICE,userRequest.getTracerId(), userRequest.getEmail());

                return BankResponse.builder()
                        .responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                        .responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)
                        .accountInfo(null)
                        .build();
            }

            log.info("{}{} CREATING NEW USER ACCOUNT IN DATABASE  {}",AccountUtils.ABS_SERVICE,userRequest.getTracerId(),userRequest);

            User newUser = User.builder()
                    .customerId(AccountUtils.BANK_PREFIX+" "+AccountUtils.generateAccountNumber())
                    .firstName(userRequest.getFirstName())
                    .lastName(userRequest.getLastName())
                    .otherName(userRequest.getOtherName())
                    .gender(userRequest.getGender())
                    .address(userRequest.getAddress())
                    .stateOfOrigin(userRequest.getStateOfOrigin())
                    .email(userRequest.getEmail())
                    .accountNumber(AccountUtils.generateAccountNumber())
                    .accountBalance(BigDecimal.ZERO)
                    .phoneNumber(userRequest.getPhoneNumber())
                    .alternativeNumber(userRequest.getAlternativeNumber())
                    .status("ACTIVE")
                    .build();

            User savedUser = userRepository.save(newUser);

            log.info("{}{} USER ACCOUNT CREATED SUCCESSFULLY. {}",AccountUtils.ABS_SERVICE,userRequest.getTracerId(),savedUser);

            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_CREATED_SUCCESSFULLY_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_CREATED_SUCCESSFULLY_MESSAGE)
                    .accountInfo(AccountInfo.builder()
                            .customerId(savedUser.getCustomerId())
                            .accountName(savedUser.getFirstName() + " " + savedUser.getLastName())
                            .accountBalance(savedUser.getAccountBalance())
                            .accountNumber(savedUser.getAccountNumber())
                            .build())
                    .build();

        } catch (Exception ex) {
            log.error("{}{} ERROR OCCURRED WHILE CREATING ACCOUNT: {}{}", AccountUtils.ABS_SERVICE,userRequest.getTracerId(),ex.getMessage(),userRequest);

            return BankResponse.builder()
                    .responseCode("500")
                    .responseMessage("INTERNAL SERVER ERROR WHILE CREATING ACCOUNT")
                    .accountInfo(null)
                    .build();
        }
    }

    @Override
    public BankResponse getUserInfo(GetUserInfoRequest request) {

        log.info("{}{} STARTING FETCH USER INFO PROCESS FOR ACCOUNT : {}",AccountUtils.ABS_SERVICE,request.getTracerId(),request.getAccountNumber());

        try {
            Optional<User> optionalUser = userRepository.findByAccountNumber(request.getAccountNumber());

            if (optionalUser.isEmpty()) {
                log.error("{}{} USER NOT FOUND FOR ACCOUNT NUMBER : {}",AccountUtils.ABS_SERVICE,request.getTracerId(),request.getAccountNumber());

                return BankResponse.builder()
                        .responseCode(AccountUtils.ACCOUNT_NOT_EXISTS_CODE)
                        .responseMessage(AccountUtils.ACCOUNT_NOT_EXISTS_MESSAGE)
                        .accountInfo(null)
                        .build();
            }

            User user = optionalUser.get();

            log.info("{}{} USER FOUND SUCCESSFULLY : {}",AccountUtils.ABS_SERVICE,request.getTracerId(),user);

            return BankResponse.builder()
                    .responseCode(AccountUtils.USER_INFO_FETCHED_SUCCESSFULLY)
                    .responseMessage(AccountUtils.USER_INFO_FETCHED_SUCCESSFULLY_MESSAGE)
                    .accountInfo(AccountInfo.builder()
                            .customerId(user.getCustomerId())
                            .accountName(user.getFirstName() + " " + user.getLastName())
                            .accountBalance(user.getAccountBalance())
                            .accountNumber(user.getAccountNumber())
                            .debitCardNumber(user.getCard() != null ? user.getCard().getCardNumber(): null)
                            .build())
                    .build();

        } catch (Exception ex) {
            log.error("{}{} ERROR WHILE FETCHING USER INFO : {}",AccountUtils.ABS_SERVICE,request.getTracerId(),ex.getMessage(),ex);

            return BankResponse.builder()
                    .responseCode(AccountUtils.SOMETHING_WENT_WRONG)
                    .responseMessage(AccountUtils.SOMETHING_WENT_WRONG_MESSAGE)
                    .accountInfo(null)
                    .build();
        }
    }

    @Override
    public BankResponse creditAccount(CreditDebitRequest request) {

        try {
            Optional<User> optionalUser = userRepository.findByAccountNumber(request.getAccountNumber());

            if (optionalUser.isEmpty()) {
                log.error("{}{} USER NOT FOUND FOR ACCOUNT NUMBER : {}", AccountUtils.ABS_SERVICE, request.getTracerId(), request.getAccountNumber());

                return BankResponse.builder()
                        .responseCode(AccountUtils.ACCOUNT_NOT_EXISTS_CODE)
                        .responseMessage(AccountUtils.ACCOUNT_NOT_EXISTS_MESSAGE)
                        .accountInfo(null)
                        .build();
            }

            User userToCredit = optionalUser.get();
            userToCredit.setAccountBalance(userToCredit.getAccountBalance().add(request.getAmount()));

            userRepository.save(userToCredit);

            return BankResponse.builder()
                    .responseCode(AccountUtils.USER_BALANCE_UPDATE_SUCCESSFULLY)
                    .responseMessage(AccountUtils.USER_BALANCE_UPDATE_SUCCESSFULLY_MESSAGE)
                    .accountInfo(AccountInfo.builder()
                            .customerId(userToCredit.getCustomerId())
                            .accountName(userToCredit.getFirstName() + " " + userToCredit.getLastName())
                            .accountBalance(userToCredit.getAccountBalance())
                            .accountNumber(userToCredit.getAccountNumber())
                            .debitCardNumber(userToCredit.getCard() != null ? userToCredit.getCard().getCardNumber() : null)
                            .build())
                    .build();

        } catch (Exception ex) {
            log.error("{}{} ERROR WHILE UPDATING USER BALANCE : {}",AccountUtils.ABS_SERVICE,request.getTracerId(),ex.getMessage(),ex);

            return BankResponse.builder()
                    .responseCode(AccountUtils.SOMETHING_WENT_WRONG)
                    .responseMessage(AccountUtils.SOMETHING_WENT_WRONG_MESSAGE)
                    .accountInfo(null)
                    .build();
        }
    }

    @Override
    public BankResponse debitAccount(CreditDebitRequest request) {

        log.info("{}{} STARTING DEBIT PROCESS FOR ACCOUNT : {} | AMOUNT : {}",AccountUtils.ABS_SERVICE,request.getTracerId(),
                request.getAccountNumber(),request.getAmount());

        try {
            Optional<User> optionalUser = userRepository.findByAccountNumber(request.getAccountNumber());

            if (optionalUser.isEmpty()) {
                log.error("{}{} USER NOT FOUND FOR ACCOUNT NUMBER : {}",AccountUtils.ABS_SERVICE,request.getTracerId(),request.getAccountNumber());

                return BankResponse.builder()
                        .responseCode(AccountUtils.ACCOUNT_NOT_EXISTS_CODE)
                        .responseMessage(AccountUtils.ACCOUNT_NOT_EXISTS_MESSAGE)
                        .accountInfo(null)
                        .build();
            }

            User userToDebit = optionalUser.get();

            if (request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {

                log.error("{}{} INVALID DEBIT AMOUNT : {}",AccountUtils.ABS_SERVICE,request.getTracerId(),request.getAmount());

                return BankResponse.builder()
                        .responseCode(AccountUtils.INVALID_AMOUNT_CODE)
                        .responseMessage(AccountUtils.INVALID_AMOUNT_MESSAGE)
                        .accountInfo(null)
                        .build();
            }

            if (userToDebit.getAccountBalance().compareTo(request.getAmount()) < 0) {

                log.error("{}{} INSUFFICIENT BALANCE | AVAILABLE : {} | REQUESTED : {}",AccountUtils.ABS_SERVICE,request.getTracerId(),
                        userToDebit.getAccountBalance(),request.getAmount());

                return BankResponse.builder()
                        .responseCode(AccountUtils.INSUFFICIENT_BALANCE_CODE)
                        .responseMessage(AccountUtils.INSUFFICIENT_BALANCE_MESSAGE)
                        .accountInfo(null)
                        .build();
            }

            BigDecimal updatedBalance = userToDebit.getAccountBalance().subtract(request.getAmount());

            userToDebit.setAccountBalance(updatedBalance);

            User updatedUser = userRepository.save(userToDebit);

            log.info("{}{} DEBIT SUCCESSFUL | NEW BALANCE : {}",AccountUtils.ABS_SERVICE,request.getTracerId(),updatedBalance);

            return BankResponse.builder()
                    .responseCode(AccountUtils.USER_BALANCE_UPDATE_SUCCESSFULLY)
                    .responseMessage(AccountUtils.USER_BALANCE_DEBIT_SUCCESSFULLY)
                    .accountInfo(AccountInfo.builder()
                            .customerId(updatedUser.getCustomerId())
                            .accountName(updatedUser.getFirstName() + " " + updatedUser.getLastName())
                            .accountBalance(updatedUser.getAccountBalance())
                            .accountNumber(updatedUser.getAccountNumber())
                            .debitCardNumber(updatedUser.getCard() != null ? updatedUser.getCard().getCardNumber() : null)
                            .build())
                    .build();

        } catch (Exception ex) {

            log.error("{}{} ERROR WHILE DEBITING USER BALANCE : {}",AccountUtils.ABS_SERVICE,request.getTracerId(),ex.getMessage(),ex);

            return BankResponse.builder()
                    .responseCode(AccountUtils.SOMETHING_WENT_WRONG)
                    .responseMessage(AccountUtils.SOMETHING_WENT_WRONG_MESSAGE)
                    .accountInfo(null)
                    .build();
        }
    }
}
