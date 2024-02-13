package com.aditumcr.todoapp.domain.value_objects;

import com.aditumcr.todoapp.domain.errors.TodoDescriptionEmptyException;

public record TodoDescription(String value) {
    public TodoDescription {
        ensureDescriptionIsNotEmpty(value);
    }

    private void ensureDescriptionIsNotEmpty(String value) {
        if (value == null || value.isBlank()) {
            throw new TodoDescriptionEmptyException();
        }
    }
}
