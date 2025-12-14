package com.AureloBank.Aurelo_Bank_Service.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "bank_user_master")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerId;
    private String firstName;
    private String lastName;
    private String otherName;
    private String gender;
    private String address;
    private String stateOfOrigin;
    private String accountNumber;
    private BigDecimal accountBalance;
    private String email;
    private String phoneNumber;
    private String alternativeNumber;
    private String status;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime modifiedAt;
    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private BankCardEntity card;


    @Override
    public String toString() {
        return "USER{" +
                "ID=" + id +
                ", CUSTOMER_ID='" + customerId + '\'' +
                ", NAME='" + firstName + " " + lastName + '\'' +
                ", GENDER='" + gender + '\'' +
                ", STATE_OF_ORIGIN='" + stateOfOrigin + '\'' +
                ", ACCOUNT_NUMBER='****" +
                (accountNumber != null && accountNumber.length() > 4
                        ? accountNumber.substring(accountNumber.length() - 4)
                        : "NA") + '\'' +
                ", ACCOUNT_BALANCE=" + accountBalance +
                ", EMAIL='****@****'" +
                ", PHONE='****" +
                (phoneNumber != null && phoneNumber.length() > 4
                        ? phoneNumber.substring(phoneNumber.length() - 4)
                        : "NA") + '\'' +
                ", STATUS='" + status + '\'' +
                ", CREATED_AT=" + createdAt +
                ", MODIFIED_AT=" + modifiedAt +
                '}';
    }


}
