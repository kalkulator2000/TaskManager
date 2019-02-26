package com.example.TaskManager.controller;

import com.example.TaskManager.controller.task_request.CreateTaskRequest;
import com.example.TaskManager.entity.Role;
import com.example.TaskManager.entity.Task;
import com.example.TaskManager.entity.User;
import com.example.TaskManager.service.TaskService;
import com.example.TaskManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/task")
@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private ExceptionController exceptionController;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping()
    public ResponseEntity<String> addTask(@RequestBody CreateTaskRequest taskRequest) {
        taskService.addTask(taskRequest);
        return new ResponseEntity<>("New task has been added to " + taskRequest.getUsername() + ".", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody CreateTaskRequest taskRequest) {
        taskService.updateTask(id, taskRequest);
        return new ResponseEntity<>("Task " + id + " has been updated.", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>("Task " + id + " has been deleted.", HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Task>> getTasks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Optional<User> user = userService.findByName(currentUserName);
        Role currentUsersRole = user.get().getRoles().get(0);

        if(currentUsersRole.getName().equals("ADMIN")) {
            List<Task> tasks = taskService.findAll();
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }
        List<Task> tasks = taskService.findByUser(user.get());
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Optional<User> user = userService.findByName(currentUserName);
        Role currentUserRole = user.get().getRoles().get(0);

        Optional<Task> task = taskService.findById(id);

        if(currentUserRole.getName().equals("ADMIN") || task.get().getUser().getName().equals(currentUserName)) {
            return new ResponseEntity<>(task.get(), HttpStatus.OK);
        }
        return null;
    }
}
