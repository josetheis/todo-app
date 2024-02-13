package com.aditumcr.todoapp.domain.value_objects;

import com.aditumcr.todoapp.domain.errors.TodoIdInvalidException;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
final public class TodoId {
    private final String value;

    public TodoId(String value) throws TodoIdInvalidException {
        ensureValidUUID(value);
        this.value = value;
    }

    private void ensureValidUUID(String value) throws TodoIdInvalidException {
        try {
            if (!UUID.fromString(value).toString().equals(value)) {
                throw new TodoIdInvalidException(value);
            }
        } catch (IllegalArgumentException e) {
            throw new TodoIdInvalidException(value);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoId todoId = (TodoId) o;
        return Objects.equals(value, todoId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
