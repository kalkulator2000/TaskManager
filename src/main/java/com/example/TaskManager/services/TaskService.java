package com.example.TaskManager.services;

import com.example.TaskManager.entities.CreateTaskRequest;
import com.example.TaskManager.entities.Task;
import com.example.TaskManager.entities.User;
import com.example.TaskManager.repositories.TaskRepository;
import com.example.TaskManager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public boolean addTask(CreateTaskRequest taskRequest) {

        // Zamiast zwracać wartość typu boolean, serwis powiniec rzucić wyjątek jeśli coś jest nie tak, a kontroler powinien wyjątek odpowiednio obsłużyć
        // Dotyczy wszystkich metod tego serwisu
        Task task = taskRequest.getTask();
        Optional<User> user = userRepository.findByName(taskRequest.getUsername());

        if(user.isPresent()) {
            task.setUser(user.get());
            taskRepository.save(task);
            return true;
        }
        return false;
    }

    public boolean updateTask(Long id, CreateTaskRequest taskRequest) {
        Optional<Task> oldtask = taskRepository.findById(id);
        if(oldtask.isPresent()) {
            Task oldTask = oldtask.get();
            Task newTask = taskRequest.getTask();
            Optional<User> userr = userRepository.findByName(taskRequest.getUsername());
            if(userr.isPresent()) {
                User user = userr.get();
                oldTask.setStartingDate(LocalDate.now());
                oldTask.setName(newTask.getName());
                oldTask.setDeadline(newTask.getDeadline());
                oldTask.setDescription(newTask.getDescription());
                oldTask.setUser(user);
                taskRepository.save(oldTask);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean deleteTask(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()){
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> findByUser(User user) {
        return taskRepository.findByUser(user);
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }
}
