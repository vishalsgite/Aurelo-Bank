package com.AureloBank.Aurelo_Bank_Service.controller;
import com.AureloBank.Aurelo_Bank_Service.dto.*;
import com.AureloBank.Aurelo_Bank_Service.service.BankCardService;
import com.AureloBank.Aurelo_Bank_Service.service.UserService;
import com.AureloBank.Aurelo_Bank_Service.util.AccountUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bank/cards")
@Slf4j
@Tag(name = "Card Network Management Endpoints")
public class BankCardController {

    private final BankCardService bankCardService;

    public BankCardController(BankCardService bankCardService){
        this.bankCardService = bankCardService;
    }

    @PostMapping("/issueDebitCard")
    public ResponseEntity<CardNetworkIssueResponse> issueDebitCard(@RequestBody CardIssueRequest request) {
       // log.info("{}{} CARD ISSUE REQUEST RECEIVED FOR ACCOUNT : {}",AccountUtils.ABS_SERVICE,request.getTracerId(),request);

        CardNetworkIssueResponse response = bankCardService.issueDebitCard(request);

        return ResponseEntity.ok(response);
    }
}
