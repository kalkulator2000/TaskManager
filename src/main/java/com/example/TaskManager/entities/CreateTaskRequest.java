package com.example.TaskManager.entities;

public class CreateTaskRequest {

    private String username;
    private Task task;

    public CreateTaskRequest() {
        super();
    }

    public CreateTaskRequest(String username, Task task) {
        this.username = username;
        this.task = task;
    }

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