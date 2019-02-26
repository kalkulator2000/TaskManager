package com.example.TaskManager.service;

import com.example.TaskManager.entity.Role;
import com.example.TaskManager.entity.User;
import com.example.TaskManager.exception.IllegalRoleException;
import com.example.TaskManager.exception.UserNotFoundException;
import com.example.TaskManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    public void createUser(User user, String role) {
        if(role.toUpperCase().equals("ADMIN") || role.toUpperCase().equals("USER")) {
            user.setPassword(encoder.encode(user.getPassword()));
            Role userRole = new Role(role.toUpperCase());
            List<Role> roles = new ArrayList<>();
            roles.add(userRole);
            user.setRoles(roles);
            userRepository.save(user);
        } else {
            throw new IllegalRoleException();
        }
    }

    public void deleteUser(String name) {
        Optional<User> userr = userRepository.findByName(name);
        if(!userr.isPresent()) throw new UserNotFoundException();

        userRepository.delete(userr.get());
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }
}
