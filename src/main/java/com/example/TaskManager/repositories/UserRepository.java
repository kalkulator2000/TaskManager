package com.example.TaskManager.repositories;

import com.example.TaskManager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
    User findByName(String name);
}
