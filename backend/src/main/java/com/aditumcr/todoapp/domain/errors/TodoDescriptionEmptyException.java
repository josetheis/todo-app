package com.aditumcr.todoapp.domain.errors;

public class TodoDescriptionEmptyException extends DomainException {
    public TodoDescriptionEmptyException() {
        super("La descripcion de un Todo no puede ser null o vacio");
    }
}
