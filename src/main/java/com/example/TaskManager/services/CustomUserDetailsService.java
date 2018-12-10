package com.example.TaskManager.services;

import com.example.TaskManager.entities.User;
import com.example.TaskManager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByName(name);

        if(user != null) {
            CustomUserDetails customUserDetails = new CustomUserDetails();
            customUserDetails.setUser(user);
            return customUserDetails;
        } else {
            throw new UsernameNotFoundException("User " + name + " not found.");
        }
    }
}
