package com.example.TaskManager.controllers;

// Ogólna uwaga do nazewnictwa - nazwy pakietów i klas powinny przedstawiać liczbę pojedynczą, nazwy pakietów powinny zawierać tylko małe litery

import com.example.TaskManager.entities.CreateTaskRequest;
import com.example.TaskManager.entities.Task;
import com.example.TaskManager.entities.User;
import com.example.TaskManager.services.TaskService;
import com.example.TaskManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    // Ogólna uwaga do URI usług REST w tej klasie - wg konwencji nie powinny one zawierać słów typu "add", "create", "update", "modify", "delete", itp.
    // Metoda HTTP, która jest określana dla każdego endpointu mówi nam, jakiego rodzaju operację chcemy wykonać.

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<String> addTask(@RequestBody CreateTaskRequest taskRequest) {
        if (taskService.addTask(taskRequest)) {
            return new ResponseEntity<>("New task has been added to " + taskRequest.getUsername() + ".", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User doesn't exist, cannot add a task.",HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody CreateTaskRequest taskRequest) {
        if (taskService.updateTask(id, taskRequest)) {
            return new ResponseEntity<>("Task " + id + " has been updated.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Task wasn't found, cannot update.", HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        if(taskService.deleteTask(id)) {
            return new ResponseEntity<>("Task " + id + " has been deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Task wasn't found, cannot delete.", HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.findAll();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // URI tej usługi nie powinien zawierać nazwy użytkownika -> możesz ją wyciągnąć z kontekstu Spring Security, którego używasz do zabezpieczenia endpointów
    @PreAuthorize("(#name == authentication.principal.username) or hasAnyAuthority('ADMIN')")
    @GetMapping("/list/{name}")
    public ResponseEntity<List<Task>> getUserTasks(@PathVariable String name) {
        Optional<User> user = userService.findByName(name);
        if (user.isPresent()) {
            List<Task> tasks = taskService.findByUser(user.get());
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        Optional<Task> task = taskService.findById(id);
        if (task.isPresent()) {
            return new ResponseEntity<>(task.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("(#name == authentication.principal.username) or hasAnyAuthority('ADMIN')")
    @GetMapping("/{name}/{id}")
    public ResponseEntity<Task> getTask(@PathVariable String name, @PathVariable Long id) {
        Optional<User> user = userService.findByName(name);
        if (user.isPresent()) {
            List<Task> tasks = user.get().getTasks();
            for (Task t : tasks) {
                if (t.getId().equals(id)) {
                    return new ResponseEntity<>(t, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
