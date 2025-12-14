package com.AureloBank.Aurelo_Bank_Service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bank_cards")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bankCode;
    private String accountNumber;
    private String cardNumber;
    private String cardType;
    private String expiryDate;
    private String status;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            unique = true
    )
    @JsonBackReference
    private User user;

    @Override
    public String toString() {
        return "BANK_CARD_ENTITY{" +
                "ID=" + id +
                ", BANK_CODE='" + bankCode + '\'' +
                ", ACCOUNT_NUMBER='****" +
                (accountNumber != null && accountNumber.length() > 4
                        ? accountNumber.substring(accountNumber.length() - 4)
                        : "NA") + '\'' +
                ", CARD_NUMBER='**** **** **** " +
                (cardNumber != null && cardNumber.length() >= 4
                        ? cardNumber.substring(cardNumber.length() - 4)
                        : "NA") + '\'' +
                ", CARD_TYPE='" + cardType + '\'' +
                ", EXPIRY_DATE='" + expiryDate + '\'' +
                ", STATUS='" + status + '\'' +
                '}';
    }

}
