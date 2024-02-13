package com.aditumcr.todoapp.domain.errors;

import com.aditumcr.todoapp.domain.value_objects.TodoId;

public class TodoAlreadyExistsException extends DomainException {
    public TodoAlreadyExistsException(TodoId id) {
        super("La tarea con ID: " + id.getValue() + " ya existe.");
    }
}
