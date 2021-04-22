package com.PayMyBuddy.services;

import com.PayMyBuddy.models.User;
import com.PayMyBuddy.models.UserConnection;
import com.PayMyBuddy.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        logger.info("Sauvegarde d'un utilisateur en base de donnée");
        return userRepository.save(user);
    }

    public User getUserByEmail(String mail){
        logger.info("Récupération d'un utilisateur grâce à son mail");
        return userRepository.getUserByMail(mail);
    }
}
