package com.example.TaskManager.service;

import com.example.TaskManager.entity.User;
import com.example.TaskManager.repository.UserRepository;
import com.example.TaskManager.security.UserDetailsImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(name);

        if(user != null) {
            UserDetailsImp userDetailsImp = new UserDetailsImp();
            userDetailsImp.setUser(user.get());
            return userDetailsImp;
        } else {
            throw new UsernameNotFoundException("User " + name + " not found.");
        }
    }
}
