package com.example.TaskManager.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 2L;

    public TaskNotFoundException() {
        super("Task not found.");
    }
}
