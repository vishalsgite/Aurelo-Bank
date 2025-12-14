package com.AureloBank.Aurelo_Bank_Service.repository;

import com.AureloBank.Aurelo_Bank_Service.entity.BankCardEntity;
import com.AureloBank.Aurelo_Bank_Service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankCardRepository extends JpaRepository<BankCardEntity, Long> {
    boolean existsByAccountNumber(String accountNumber);
    boolean existsByUser(User user);
}

