package com.example.TaskManager.repositories;

import com.example.TaskManager.entities.Task;
import com.example.TaskManager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}
