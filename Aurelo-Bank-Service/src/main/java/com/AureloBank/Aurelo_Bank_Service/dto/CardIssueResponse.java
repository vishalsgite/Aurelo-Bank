package com.AureloBank.Aurelo_Bank_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardIssueResponse {

    private String status;          // SUCCESS / FAILED
    private String cardNumber;      // Full card number (store masked if needed)
    private String expiryDate;      // MM/YY
    private String cvv;             // 3-digit CVV
    private String cardNetwork;     // VISA-LIKE  etc.
    private LocalDateTime issuedAt; // Card issued timestamp

    @Override
    public String toString() {
        return "CARD_ISSUE_RESPONSE{" +
                "STATUS='" + status + '\'' +
                ", CARD_NUMBER='**** **** **** " +
                (cardNumber != null && cardNumber.length() >= 4
                        ? cardNumber.substring(cardNumber.length() - 4)
                        : "NA") + '\'' +
                ", EXPIRY_DATE='" + expiryDate + '\'' +
                ", CVV='***'" +
                ", CARD_NETWORK='" + cardNetwork + '\'' +
                ", ISSUED_AT=" + issuedAt +
                '}';
    }

}
