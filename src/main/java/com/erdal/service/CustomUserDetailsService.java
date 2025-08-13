package com.erdal.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Burada DB'den kullanıcı çekebilirsin
        // Şimdilik dummy user dönelim
        return User.builder()
                .username(username)
                .password("{noop}password") // {noop} = şifre encode edilmez
                .authorities(Collections.emptyList())
                .build();
    }
}