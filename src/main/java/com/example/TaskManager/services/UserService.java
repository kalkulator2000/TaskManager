package com.example.TaskManager.services;

import com.example.TaskManager.entities.Role;
import com.example.TaskManager.entities.User;
import com.example.TaskManager.repositories.UserRepository;
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

    // Idąc za komentarzem z klasy 'UserController' - te dwie metody serwisu wyglądają identycznie, różniąc się tylko nazwą roli przypisywanej do użytkownika.
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
    }

    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    // Nie zostawiaj nieużywanego kodu
    public Optional<User> findOne(String email) {
        return userRepository.findByEmail(email);
    }

    // Jak wyżej
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
