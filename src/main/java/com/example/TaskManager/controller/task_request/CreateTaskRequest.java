package com.example.TaskManager.controller.task_request;

import com.example.TaskManager.entity.Task;

public class CreateTaskRequest {

    private String username;
    private Task task;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}