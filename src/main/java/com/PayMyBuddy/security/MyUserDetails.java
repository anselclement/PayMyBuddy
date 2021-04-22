package com.PayMyBuddy.security;

import com.PayMyBuddy.models.User;
import com.PayMyBuddy.models.UserConnection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {

    private User user;

    public MyUserDetails(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    public String getFirstName(){
        return user.getFirstName();
    }

    public Integer getId(){
        return user.getId();
    }

    public String getLastName(){
        return user.getLastName();
    }

    public int getWallet() {
        return user.getWallet();
    }

    public List<UserConnection> getListConnections(){
        return user.getConnections();
    }

    @Override
    public String getUsername() {
        return user.getMail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
