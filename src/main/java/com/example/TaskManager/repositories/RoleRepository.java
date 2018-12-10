package com.example.TaskManager.repositories;

import com.example.TaskManager.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
