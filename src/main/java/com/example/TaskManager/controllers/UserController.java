package com.example.TaskManager.controllers;

import com.example.TaskManager.entities.User;
import com.example.TaskManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {
        userService.createUser(user);
        return "User "+ user.getName()+" added successfully";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/addAdmin")
    public String addAdmin(@RequestBody User user) {
        userService.createAdmin(user);
        return "Admin "+ user.getName()+" added successfully";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/deleteUser/{name}")
    public ResponseEntity<String> deleteUser(@PathVariable String name) {
        Optional<User> user = userService.findByName(name);
        if(user.isPresent()) {
            userService.deleteUser(user.get());
            return new ResponseEntity<>("User " + name + " deleted.",HttpStatus.OK);
        }
        return new ResponseEntity<>("User " + name + " could't be found.",HttpStatus.NOT_FOUND);
    }

}
