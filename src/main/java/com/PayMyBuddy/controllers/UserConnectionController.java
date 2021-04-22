package com.PayMyBuddy.controllers;

import com.PayMyBuddy.models.User;
import com.PayMyBuddy.models.UserConnection;
import com.PayMyBuddy.security.MyUserDetails;
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
public class UserConnectionController {

    private static final Logger logger = LoggerFactory.getLogger(UserConnectionController.class);

    @Autowired
    private UserConnectionService userConnectionService;

    @Autowired
    private UserService userService;

    @PostMapping("/addContact")
    public String saveContact(@RequestParam(value = "mail") String mail, @AuthenticationPrincipal MyUserDetails userDetails){
        String userMail = userDetails.getUsername();
        User user = userService.getUserByEmail(userMail);
        User getInfoContact = userService.getUserByEmail(mail);
        if (getInfoContact == null){
            return "redirect:/home/contact";
        }
        UserConnection newUserConnection = new UserConnection();
        newUserConnection.setUser_id(user);
        newUserConnection.setFirstName(getInfoContact.getFirstName());
        newUserConnection.setLastName(getInfoContact.getLastName());
        newUserConnection.setMail(getInfoContact.getMail());
        userConnectionService.saveContact(newUserConnection);
        return "redirect:/home/contact";
    }

    @GetMapping("/deleteContact/{id}")
    public String deleteUserContact(@PathVariable(name = "id") Integer id){
        userConnectionService.deleteContactById(id);
        return "redirect:/home/contact";
    }
}
