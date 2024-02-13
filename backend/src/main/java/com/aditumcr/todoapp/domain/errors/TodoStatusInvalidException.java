package com.aditumcr.todoapp.domain.errors;

public class TodoStatusInvalidException extends DomainException {
    public TodoStatusInvalidException() {
        super("El valor de status no es valido");
    }
}
