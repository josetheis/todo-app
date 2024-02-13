package com.aditumcr.todoapp.domain.errors;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException() {
        super("La tarea no pudo ser encontrada");
    }
}
