package com.aditumcr.todoapp.domain.errors;

public class TodoIdInvalidException extends DomainException {
    public TodoIdInvalidException(String id) {
        super("El ID: " + id + " no es un UUID valido.");
    }
}
