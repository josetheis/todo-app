package com.aditumcr.todoapp.domain.value_objects;

import com.aditumcr.todoapp.domain.Status;
import com.aditumcr.todoapp.domain.errors.TodoStatusInvalidException;

public record TodoStatus(Status value) {
    public TodoStatus {
        ensureValueIsNotNull(value);
    }

    public TodoStatus(String value) {
        this(Status.getByName(value));
    }

    private void ensureValueIsNotNull(Status value) {
        if(value == null) {
            throw new TodoStatusInvalidException();
        }
    }
}
