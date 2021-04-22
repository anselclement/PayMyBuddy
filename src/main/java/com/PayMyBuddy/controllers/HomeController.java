package com.PayMyBuddy.controllers;

import com.PayMyBuddy.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public String homepage(){
        logger.info("Affichage de la page de connexion");
        return "homepage";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        logger.info("Affichage de la page d'enregistrement d'un nouvel utilisateur");
        model.addAttribute("user", new User());
        return "registration";
    }

}
