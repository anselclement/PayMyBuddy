package com.PayMyBuddy.controllers;

import com.PayMyBuddy.models.BankAccount;
import com.PayMyBuddy.models.Transaction;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.models.UserConnection;
import com.PayMyBuddy.security.MyUserDetails;
import com.PayMyBuddy.services.BankAccountService;
import com.PayMyBuddy.services.TransactionService;
import com.PayMyBuddy.services.UserConnectionService;
import com.PayMyBuddy.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserConnectionService userConnectionService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping("/home")
    public String home(){
        logger.info("Affichage de la page d'accueil de l'utilisateur");
        return "home";
    }

    @PostMapping("/user_registration")
    public String registration(@Valid User user, BindingResult bindingResult){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        if (bindingResult.hasErrors()){
            return "registration";
        }
        userService.saveUser(user);

        return "redirect:/login";
    }

    @GetMapping("/home/profile")
    public String userProfile(@AuthenticationPrincipal MyUserDetails userDetails, Model model){
        String userMail = userDetails.getUsername();
        User user = userService.getUserByEmail(userMail);
        List<BankAccount> listUserBankAccount= bankAccountService.getAllUserBankAccountById(user.getId());
        model.addAttribute("listBankAccount",listUserBankAccount);
        return "profile";
    }

    @GetMapping("/home/transfer")
    public String transfer(@AuthenticationPrincipal MyUserDetails userDetails, Model model){
        String userMail = userDetails.getUsername();
        User user = userService.getUserByEmail(userMail);
        double userWallet = user.getWallet();
        model.addAttribute("userWallet", userWallet);
        List<UserConnection> listUserContact = userConnectionService.getAllUserContactById(user.getId());
        model.addAttribute("listContact",listUserContact);
        List<BankAccount> listUserBankAccount = bankAccountService.getAllUserBankAccountById(user.getId());
        model.addAttribute("listBankAccount", listUserBankAccount);
        List<Transaction> listUserTransaction = transactionService.getAllUserTransactionById(user.getId());
        model.addAttribute("listTransactions", listUserTransaction);
        return "transfer";
    }

    @GetMapping("/home/contact")
    public String contact(@AuthenticationPrincipal MyUserDetails userDetails, Model model){
        String userMail = userDetails.getUsername();
        User user = userService.getUserByEmail(userMail);
        List<UserConnection> listUserContact = userConnectionService.getAllUserContactById(user.getId());
        model.addAttribute("listContact",listUserContact);
        return "contact";
    }


}
