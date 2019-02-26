package com.example.TaskManager.exception;

public class IllegalRoleException extends RuntimeException {

    private static final long serialVersionUID = 3L;

    public IllegalRoleException() {
        super("Illegal role.");
    }
}
