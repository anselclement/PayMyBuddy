package com.PayMyBuddy.services;

import com.PayMyBuddy.models.Transaction;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private static Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction saveTransaction(Transaction transaction){
        logger.info("Sauvegarde d'une transaction en base de donnée");
        return transactionRepository.save(transaction);
    }
}
