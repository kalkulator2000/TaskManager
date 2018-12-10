package com.example.TaskManager.controllers;

import com.example.TaskManager.entities.Task;
import com.example.TaskManager.entities.User;
import com.example.TaskManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String deleteUser(@PathVariable String name) {
        User user = userService.findByName(name);
        userService.deleteUser(user);
        return "User " + name + " deleted.";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/users")
    public String getUsers() {
        StringBuilder sb = new StringBuilder("Listing all the users:\n");
        List<User> users =  userService.findByRoleUser();
        for(User u : users) {
            sb.append("\t"+u.toString() + "\n");
        }
        return sb.toString();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/usersTasks")
    public String getAllUsersTasks() {
        StringBuilder sb = new StringBuilder("Listing all the users and their tasks:\n");
        List<User> users =  userService.findAll();
        for(User u : users) {
            List<Task> tasks = u.getTasks();
            sb.append("\t"+u.toString() + "\n");
            for(Task t : tasks) {
                sb.append("\t\t" + t.showTask() + "\n");
            }
        }
        return sb.toString();
    }

    @GetMapping("/{username}")
    @PreAuthorize("(#username == authentication.principal.username) or hasAnyAuthority('ADMIN')")
    public String getUserTasks(@PathVariable String username) {
        StringBuilder sb = new StringBuilder("Tasks for user " + username + "\n");
        List<Task> tasks = userService.findByName(username).getTasks();
        for(Task t : tasks) {
            sb.append("\t"+t.showDetailedTask() + "\n");
        }
        return sb.toString();
    }

}
