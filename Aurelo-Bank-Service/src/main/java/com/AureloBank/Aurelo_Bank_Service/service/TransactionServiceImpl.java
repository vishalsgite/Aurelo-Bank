package com.AureloBank.Aurelo_Bank_Service.service;

import com.AureloBank.Aurelo_Bank_Service.entity.Transactions;
import com.AureloBank.Aurelo_Bank_Service.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public void saveTransactions(Transactions transactions) {

    }
}
