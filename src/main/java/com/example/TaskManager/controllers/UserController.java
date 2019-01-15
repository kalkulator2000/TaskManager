package com.example.TaskManager.controllers;

import com.example.TaskManager.entities.User;
import com.example.TaskManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// Brakuje fragmentu URI dla całego kontrolera
@RestController
public class UserController {

    @Autowired
    UserService userService;

    // Czy jest potrzebna osobna usługa do tworzenia administratora, a osobna dla zwykłego użytkownika?
    // Metody kontrolerów REST powinny zwracać typ ResponseEntity<?>
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
        // Skoro masz wartwę serwisu z metodami dla zarządzania użytkownikiem, to warto przenieść logikę do tamtej warstwy - tam sprawdzaj, czy użytkownik istnieje
        Optional<User> user = userService.findByName(name);
        if(user.isPresent()) {
            userService.deleteUser(user.get());
            return new ResponseEntity<>("User " + name + " deleted.",HttpStatus.OK);
        }
        return new ResponseEntity<>("User " + name + " could't be found.",HttpStatus.NOT_FOUND);
    }

}
