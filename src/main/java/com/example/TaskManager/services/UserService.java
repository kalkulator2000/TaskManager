package com.example.TaskManager.services;

import com.example.TaskManager.entities.Role;
import com.example.TaskManager.entities.User;
import com.example.TaskManager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder encoder;

    public void createUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        Role userRole = new Role("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
    }

    public void createAdmin(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        Role userRole = new Role("ADMIN");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.deleteById(user.getEmail());
//        userRepository.delete(user);
    }

    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    public User findOne(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findByRoleUser() {
        List<User> users = userRepository.findAll();
        List<User> userUser = new ArrayList<>();
        for(User u : users) {
            if(!u.getRoles().get(0).getName().equals("ADMIN"))  {
                userUser.add(u);
            }
        }
        return userUser;
    }
}
