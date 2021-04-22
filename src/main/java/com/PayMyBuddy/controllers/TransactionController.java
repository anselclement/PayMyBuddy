package com.PayMyBuddy.controllers;

import com.PayMyBuddy.models.Transaction;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.security.MyUserDetails;
import com.PayMyBuddy.services.TransactionService;
import com.PayMyBuddy.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @PostMapping("/saveTransaction")
    public String saveTransaction(@RequestParam(value = "description") String description, @RequestParam(value = "connection") String connection, @RequestParam(value = "amount") double amount, @AuthenticationPrincipal MyUserDetails userDetails){
        String userMail = userDetails.getUsername();
        User user = userService.getUserByEmail(userMail);
        double userWallet = user.getWallet();
        User userConnection = userService.getUserByEmail(connection);
        double userConnectionWallet = userConnection.getWallet();
        if(amount == 0) {
            return "redirect:/home/transfer";
        }else{
            userWallet = userWallet - amount;
            user.setWallet(userWallet);
            amount = (amount - ((amount/100)*0.5));
            userConnectionWallet = userConnectionWallet + amount;
            userConnection.setWallet(userConnectionWallet);
        }
        Transaction transaction = new Transaction();
        transaction.setUser_id(user);
        transaction.setConnection(connection);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transactionService.saveTransaction(transaction);
        return "redirect:/home/transfer";
    }

    @PostMapping("/saveTransactionBankAccount")
    public String saveTransactionBankAccount(@RequestParam(value = "description") String description, @RequestParam(value = "name") String name, @RequestParam(value = "amount") double amount, @AuthenticationPrincipal MyUserDetails userDetails){
        String userMail = userDetails.getUsername();
        User user = userService.getUserByEmail(userMail);

        double userWallet = user.getWallet();
        userWallet = userWallet + amount;
        if (userWallet >= 0){
            user.setWallet(userWallet);
        }else{
            return "redirect:/home/transfer";
        }
        Transaction transaction = new Transaction();
        transaction.setUser_id(user);
        transaction.setConnection(name);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transactionService.saveTransaction(transaction);
        return "redirect:/home/transfer";
    }

}
