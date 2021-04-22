package com.PayMyBuddy.repository;

import com.PayMyBuddy.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

    @Query(value = "Select id, connection, amount, description, user_id from transactions where user_id = :id", nativeQuery = true)
    List<Transaction> getAllUserTransactionById(int id);

}
