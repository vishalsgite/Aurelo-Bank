package com.AureloBank.Aurelo_Bank_Service.util;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;

public class AccountUtils {

    public static final String ABS_SERVICE = "ABS_SERVICE_";
    public static final String ACCOUNT_CREATED_SUCCESSFULLY_CODE = "ABSU0000";
    public static final String ACCOUNT_EXISTS_CODE = "ABSU0001";
    public static final String ACCOUNT_NOT_EXISTS_CODE = "ABSU0002";
    public static final String ACCOUNT_CREATED_SUCCESSFULLY_MESSAGE = "ACCOUNT CREATED SUCCESSFULLY";
    public static final String ACCOUNT_EXISTS_MESSAGE = "USER EXISTS ALREADY";
    public static final String ACCOUNT_NOT_EXISTS_MESSAGE = "USER NOT EXISTS";
    public static final String BANK_PREFIX = "ABS";
    public static final String BANK_CODE_PREFIX = "1001";
    public static final String DEBIT_CARD = "DEBIT_CARD";
    public static final String CREDIT_CARD = "CREDIT_CARD";
    public static final String USER_INFO_FETCHED_SUCCESSFULLY = "ABSU1111";
    public static final String USER_BALANCE_UPDATE_SUCCESSFULLY = "ABSU9999";
    public static final String USER_BALANCE_DEBIT_SUCCESSFULLY = "USER_BALANCE_DEBIT_SUCCESSFULLY";
    public static final String USER_INFO_FETCHED_SUCCESSFULLY_MESSAGE = "USER_INFO_FETCHED_SUCCESSFULLY";
    public static final String USER_BALANCE_UPDATE_SUCCESSFULLY_MESSAGE = "USER_BALANCE_UPDATE_SUCCESSFULLY_MESSAGE";
    public static final String SOMETHING_WENT_WRONG= "ABSU4444";
    public static final String SOMETHING_WENT_WRONG_MESSAGE= "SOMETHING_WENT_WRONG_MESSAGE";
    public static final String INVALID_AMOUNT_CODE = "ABS01";
    public static final String INVALID_AMOUNT_MESSAGE = "INVALID DEBIT AMOUNT";
    public static final String INSUFFICIENT_BALANCE_CODE = "ABSU2222";
    public static final String INSUFFICIENT_BALANCE_MESSAGE = "INSUFFICIENT ACCOUNT BALANCE";


    public static String generateAccountNumber() {
        String year = String.valueOf(Year.now());

        int min = 1000;
        int max = 9999;
        int randomNumber = (int) Math.floor(Math.random() * (max - min + 1) + min);
        String randomNum = String.valueOf(randomNumber);

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMM");
        String datePart = today.format(formatter);
        return new StringBuilder()
                .append(year)
                .append(randomNum)
                .append(datePart)
                .toString();
    }
}
