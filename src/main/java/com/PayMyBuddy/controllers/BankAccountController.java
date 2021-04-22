package com.PayMyBuddy.controllers;

import com.PayMyBuddy.models.BankAccount;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.models.UserConnection;
import com.PayMyBuddy.security.MyUserDetails;
import com.PayMyBuddy.services.BankAccountService;
import com.PayMyBuddy.services.UserConnectionService;
import com.PayMyBuddy.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class BankAccountController {

    private static final Logger logger = LoggerFactory.getLogger(BankAccountController.class);

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private UserService userService;

    @PostMapping("/addBankAccount")
    public String saveBankAccount(@RequestParam(value = "name") String name, @RequestParam(value = "number") int number, @AuthenticationPrincipal MyUserDetails userDetails){
        String userMail = userDetails.getUsername();
        User user = userService.getUserByEmail(userMail);
        BankAccount newBankAccount = new BankAccount();
        newBankAccount.setName(name);
        newBankAccount.setNumber(number);
        newBankAccount.setUser_id(user);
        bankAccountService.saveBankAccount(newBankAccount);
        return "redirect:/home/profile";
    }

    @GetMapping("/deleteBankAccount/{id}")
    public String deleteUserBankAccount(@PathVariable(name = "id") Integer id){
        bankAccountService.deleteBankAccountById(id);
        return "redirect:/home/profile";
    }
}
