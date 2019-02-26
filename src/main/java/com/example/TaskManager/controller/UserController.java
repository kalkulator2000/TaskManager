package com.example.TaskManager.controller;

import com.example.TaskManager.entity.User;
import com.example.TaskManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/{role}")
    public ResponseEntity<String> addUser(@RequestBody User user, @PathVariable String role) {
        userService.createUser(user, role);
        return new ResponseEntity<>("User " + user.getName() + " (" + role + ") " + " added successfully.", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteUser(@PathVariable String name) {
        userService.deleteUser(name);
        return new ResponseEntity<>("User " + name + " deleted.", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<User>> listUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

}
