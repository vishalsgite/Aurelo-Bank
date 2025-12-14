package com.AureloBank.Aurelo_Bank_Service.service;

import com.AureloBank.Aurelo_Bank_Service.dto.CardIssueRequest;
import com.AureloBank.Aurelo_Bank_Service.dto.CardNetworkIssueResponse;
import com.AureloBank.Aurelo_Bank_Service.dto.IssueCardDetails;
import com.AureloBank.Aurelo_Bank_Service.entity.BankCardEntity;
import com.AureloBank.Aurelo_Bank_Service.entity.User;
import com.AureloBank.Aurelo_Bank_Service.repository.BankCardRepository;
import com.AureloBank.Aurelo_Bank_Service.repository.UserRepository;
import com.AureloBank.Aurelo_Bank_Service.util.AccountUtils;
import com.AureloBank.Aurelo_Bank_Service.util.NetworkResponseCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankCardServiceImpl implements BankCardService {

    private final WebClient cardNetworkWebClient;
    private final BankCardRepository bankCardRepository;
    private final UserRepository userRepository;

    @Override
    public CardNetworkIssueResponse issueDebitCard(CardIssueRequest request) {

        log.info("{}{} STARTING DEBIT CARD ISSUANCE PROCESS : {}",AccountUtils.ABS_SERVICE,request.getTracerId(),request);

        try {
            User user = userRepository.findByAccountNumber(request.getAccountNumber())
                    .orElseThrow(() -> {
                        log.error("{}{} USER NOT FOUND FOR ACCOUNT NUMBER : {}",AccountUtils.ABS_SERVICE,request.getTracerId(),request.getAccountNumber());
                        return new RuntimeException("USER NOT FOUND");
                    });

            log.info("{}{} USER FOUND SUCCESSFULLY : {}",AccountUtils.ABS_SERVICE,request.getTracerId(),user);

            if (bankCardRepository.existsByUser(user)) {
                log.error("{}{} DEBIT CARD ALREADY EXISTS FOR USER : {}",AccountUtils.ABS_SERVICE,request.getTracerId(),user);
                throw new RuntimeException("USER ALREADY HAS A DEBIT CARD");
            }

            CardIssueRequest cardNetworkRequest = CardIssueRequest.builder()
                    .tracerId(request.getTracerId())
                    .bankCode(AccountUtils.BANK_CODE_PREFIX)
                    .accountName(request.getAccountName())
                    .accountNumber(request.getAccountNumber())
                    .cardType(AccountUtils.DEBIT_CARD)
                    .build();

            log.info("{}{} SENDING REQUEST TO CARD NETWORK : {}",AccountUtils.ABS_SERVICE,request.getTracerId(),cardNetworkRequest);

            CardNetworkIssueResponse networkResponse = cardNetworkWebClient
                    .post()
                    .uri("/api/cards/issueDebitCard")
                    .bodyValue(cardNetworkRequest)
                    .retrieve()
                    .bodyToMono(CardNetworkIssueResponse.class)
                    .block();

            if (networkResponse == null) {
                log.error("{}{} NULL RESPONSE RECEIVED FROM CARD NETWORK",AccountUtils.ABS_SERVICE,request.getTracerId());
                throw new RuntimeException("CARD NETWORK UNAVAILABLE");
            }

            if (!NetworkResponseCodes.SUCCESS.equals(networkResponse.getResponseCode()) || networkResponse.getIssueCardDetails() == null) {

                log.error("{}{} CARD NETWORK FAILURE | CODE : {} | MESSAGE : {}",AccountUtils.ABS_SERVICE,request.getTracerId(),networkResponse.getResponseCode(),
                        networkResponse.getResponseMessage());

                return networkResponse;
            }

            log.info("{}{} CARD NETWORK RESPONSE SUCCESS : {}",AccountUtils.ABS_SERVICE,request.getTracerId(),networkResponse);

            IssueCardDetails details = networkResponse.getIssueCardDetails();

            BankCardEntity cardEntity = BankCardEntity.builder()
                    .bankCode(cardNetworkRequest.getBankCode())
                    .accountNumber(request.getAccountNumber())
                    .cardNumber(details.getCardNumber())
                    .cardType(cardNetworkRequest.getCardType())
                    .expiryDate(details.getExpiryDate())
                    .status("ACTIVE")
                    .user(user)
                    .build();

            bankCardRepository.save(cardEntity);

            log.info("{}{} DEBIT CARD SAVED SUCCESSFULLY FOR USER : {}",AccountUtils.ABS_SERVICE,request.getTracerId(),user);

            return networkResponse;

        } catch (Exception ex) {

            log.error("{}{} ERROR DURING DEBIT CARD ISSUANCE | REQUEST : {} | ERROR : {}",AccountUtils.ABS_SERVICE,request.getTracerId(),request,ex.getMessage(),ex);

            throw ex;
        }
    }
}
