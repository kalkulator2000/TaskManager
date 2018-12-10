package com.example.TaskManager.services;

import com.example.TaskManager.entities.Task;
import com.example.TaskManager.entities.User;
import com.example.TaskManager.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public void addTask(Task task, User user) {
        task.setUser(user);
        taskRepository.save(task);
    }

    public void updateTask(Long id, Task newTask) {
        Task oldTask = taskRepository.findById(id).get();
        User user = oldTask.getUser();
        taskRepository.delete(oldTask);
        addTask(newTask,user);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> findByUser(User user) {
        return taskRepository.findByUser(user);
    }
}
