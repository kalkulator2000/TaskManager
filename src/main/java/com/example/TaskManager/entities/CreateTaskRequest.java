package com.example.TaskManager.entities;

// Ta klasa nie jest encją bazodanową, możesz ją śmiało przenieść do jakiegoś nowego, pomocniczego pakietu w pakiecie "controllers"
public class CreateTaskRequest {

    private String username;
    private Task task;

    // wywołanie "super()" dla klasy, która niczego nie dziedziczy... co miałeś na myśli? :) poza tym ten konstruktor nie jest używany
    public CreateTaskRequest() {
        super();
    }

    // Nie zostawiaj nieużywanego kodu
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