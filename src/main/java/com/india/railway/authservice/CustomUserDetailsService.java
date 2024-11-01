package com.india.railway.authservice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.india.railway.repository.UserRepository;
import java.util.List;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        com.india.railway.model.User user = userRepository.findByUsername(username);
        // Here we are converting into spring security user by passing arguments
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        // return user.getRoles().stream()
        // .map(role -> new SimpleGrantedAuthority(role.getName()))
        // .collect(Collectors.toList());
        List<GrantedAuthority> arr = new ArrayList<>();
        arr.add(new SimpleGrantedAuthority("admin"));
        return arr;
    }
}
