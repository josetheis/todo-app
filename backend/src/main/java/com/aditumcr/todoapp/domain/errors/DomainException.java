package com.aditumcr.todoapp.domain.errors;

public abstract class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}
