package com.PayMyBuddy.repository;

import com.PayMyBuddy.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
