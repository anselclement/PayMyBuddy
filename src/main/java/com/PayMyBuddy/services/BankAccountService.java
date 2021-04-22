package com.PayMyBuddy.services;

import com.PayMyBuddy.models.BankAccount;
import com.PayMyBuddy.models.UserConnection;
import com.PayMyBuddy.repository.BankAccountRepository;
import com.PayMyBuddy.repository.UserConnectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {

    private static Logger logger = LoggerFactory.getLogger(BankAccountService.class);

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public BankAccount saveBankAccount(BankAccount bankAccount){
        logger.info("Sauvegarde d'un compte bancaire en base de donnée");
        return bankAccountRepository.save(bankAccount);
    }

    public List<BankAccount> getAllUserBankAccountById(Integer id){
        logger.info("Récupération de la liste des comptes bancaires de l'utilisateur");
        return bankAccountRepository.getAllUserBankAccountById(id);
    }

    public void deleteBankAccountById(Integer id){
        logger.info("Suppression du compte bancaire de la liste de l'utilisateur");
        bankAccountRepository.deleteById(id);
    }
}
