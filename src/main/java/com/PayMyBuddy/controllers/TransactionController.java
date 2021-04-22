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

@Controller
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @PostMapping("/saveTransaction")
    public String saveTransaction(@RequestParam(value = "connection") String connection, @RequestParam(value = "amount") String amount, @AuthenticationPrincipal MyUserDetails userDetails){
        String userMail = userDetails.getUsername();
        User user = userService.getUserByEmail(userMail);
        Transaction transaction = new Transaction();
        transaction.setUser_id(user);
        transaction.setConnection(connection);
        transaction.setAmount(amount);
        transactionService.saveTransaction(transaction);
        //TODO : logique de paiement
        return "redirect:/home/transfer";
    }
}
