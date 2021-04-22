package com.PayMyBuddy.services;

import com.PayMyBuddy.models.UserConnection;
import com.PayMyBuddy.repository.UserConnectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserConnectionService {

    private static Logger logger = LoggerFactory.getLogger(UserConnectionService.class);

    @Autowired
    private UserConnectionRepository userConnectionRepository;

    public UserConnection saveContact(UserConnection userConnection){
        logger.info("Sauvegarde d'un contact en base de donnée");
        return userConnectionRepository.save(userConnection);
    }

    public List<UserConnection> getAllUserContactById(Integer id){
        logger.info("Récupération de la liste de contact de l'utilisateur");
        return userConnectionRepository.getAllUserContactById(id);
    }

    public void deleteContactById(Integer id){
        logger.info("Suppression du contact de la liste de l'utilisateur");
        userConnectionRepository.deleteById(id);
    }
}
