package com.example.TaskManager.controllers;

import com.example.TaskManager.entities.Task;
import com.example.TaskManager.entities.User;
import com.example.TaskManager.services.TaskService;
import com.example.TaskManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Dobrą praktyką jest używanie @RequestMapping dla całego kontrolera, żeby wyznaczyć "domenę" w jakiej ten kontroler działa - w tym przypadku są to wszystkie metody dotyczące zadań
@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    // Metoda POST raczej nie powinna zawierać parameterów w URLu.
    // Wiem, że żeby zapisać encję Task potrzebujemy przypisać istniejącą encję User i ciężko byłoby to przekazać jako RequestBody, ale są na to inne sposoby -
    // - możemy stworzyć sobie typ pomocniczy, np. CreateTaskRequest, który zawierałby nazwę użytkownika i szczegóły zadania do stworzenia
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/addTask/{name}")
    public String addTask(@PathVariable String name, @RequestBody Task task) {
        // Dobre miejsce na użycie Optional<...>
        User user = userService.findByName(name);
        if (!userService.isAdmin(user)) {
            taskService.addTask(task, user);
            return "User " + name + " has a new task.";
        }
        // Dotyczy wszystkich metod:
        // Spring daje nam pomocniczy typ ResponseEntity<...>, jest on wygodny w przypadku, gdy chcemy zwrócić pełną odpowiedź HTTP, razem z kodem odpowiedzi i np. komentarzem.
        // "Cannot add task to the admin"
        return "Cannot add task to the admin";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/updateTask/{id}")
    public String updateTask(@PathVariable Long id, @RequestBody Task task) {
        taskService.updateTask(id, task);
        return "Task " + task.getId() + " for user " + task.getUser().getName() + " has been updated.";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "Task " + id + " has been deleted.";
    }

    // 1. Ta metoda powinna zwracać listę obiektów, a nie łańcuch znaków!
    // 2. Jak zwrócić listę zadań dla danego użytkownika? (jedno z założeń projektu)
    // 3. Jak zwrócić jedno zadanie?
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/tasks")
    public String getTasks() {
        StringBuilder sb = new StringBuilder("Listing all the tasks:\n");
        List<Task> tasks = taskService.findAll();
        for (Task t : tasks) {
            sb.append("\t" + t.showTask() + "\n");
        }
        return sb.toString();
    }
}
