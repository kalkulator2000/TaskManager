package com.example.TaskManager.controllers;

import com.example.TaskManager.entities.Task;
import com.example.TaskManager.entities.User;
import com.example.TaskManager.services.TaskService;
import com.example.TaskManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/addTask/{name}")
    public String addTask(@PathVariable String name, @RequestBody Task task) {
        User user = userService.findByName(name);
        if(!userService.isAdmin(user)) {
            taskService.addTask(task, user);
            return "User " + name + " has a new task.";
        }
        return "Cannot add task to the admin";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/updateTask/{id}")
    public String updateTask(@PathVariable Long id, @RequestBody Task task) {
        taskService.updateTask(id,task);
        return "Task " + task.getId() + " for user " + task.getUser().getName() + " has been updated.";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "Task " + id + " has been deleted.";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/tasks")
    public String getTasks() {
        StringBuilder sb = new StringBuilder("Listing all the tasks:\n");
        List<Task> tasks = taskService.findAll();
        for(Task t : tasks) {
            sb.append("\t"+t.showTask() + "\n");
        }
        return sb.toString();
    }
}
