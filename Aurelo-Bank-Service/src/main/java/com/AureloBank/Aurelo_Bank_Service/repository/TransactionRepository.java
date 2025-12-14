package com.AureloBank.Aurelo_Bank_Service.repository;

import com.AureloBank.Aurelo_Bank_Service.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transactions,Long> {
}
