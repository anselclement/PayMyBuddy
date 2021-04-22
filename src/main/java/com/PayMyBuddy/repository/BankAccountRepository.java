package com.PayMyBuddy.repository;

import com.PayMyBuddy.models.BankAccount;
import com.PayMyBuddy.models.UserConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

    @Query(value = "Select id, name, number, user_id from bank_account where user_id = :id", nativeQuery = true)
    List<BankAccount> getAllUserBankAccountById(int id);
}
