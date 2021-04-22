package com.PayMyBuddy.services;

import com.PayMyBuddy.models.User;
import com.PayMyBuddy.repository.UserRepository;
import com.PayMyBuddy.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        User user = userRepository.getUserByMail(mail);

        if(user == null){
            throw new UsernameNotFoundException("L'utilisateur n'existe pas");
        }

        return new MyUserDetails(user);
    }
}
