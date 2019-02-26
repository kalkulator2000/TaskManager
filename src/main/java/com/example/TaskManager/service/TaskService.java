package com.example.TaskManager.service;

import com.example.TaskManager.controller.task_request.CreateTaskRequest;
import com.example.TaskManager.entity.Task;
import com.example.TaskManager.entity.User;
import com.example.TaskManager.exception.TaskNotFoundException;
import com.example.TaskManager.exception.UserNotFoundException;
import com.example.TaskManager.repository.TaskRepository;
import com.example.TaskManager.repository.UserRepository;
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

    public void addTask(CreateTaskRequest taskRequest) {
        Task task = taskRequest.getTask();
        Optional<User> user = userRepository.findByName(taskRequest.getUsername());

        if (!user.isPresent()) throw new UserNotFoundException();

        task.setUser(user.get());
        taskRepository.save(task);
    }

    public void updateTask(Long id, CreateTaskRequest taskRequest) {
        Optional<Task> oldtask = taskRepository.findById(id);

        if (!oldtask.isPresent()) throw new TaskNotFoundException();

        Task oldTask = oldtask.get();
        Task newTask = taskRequest.getTask();
        Optional<User> userr = userRepository.findByName(taskRequest.getUsername());

        if (!userr.isPresent()) throw new UserNotFoundException();

        User user = userr.get();
        oldTask.setStartingDate(LocalDate.now());
        oldTask.setName(newTask.getName());
        oldTask.setDeadline(newTask.getDeadline());
        oldTask.setDescription(newTask.getDescription());
        oldTask.setUser(user);
        taskRepository.save(oldTask);
    }

    public void deleteTask(Long id) {
        Optional<Task> task = taskRepository.findById(id);

        if (!task.isPresent()) throw new TaskNotFoundException();

        taskRepository.deleteById(id);
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
