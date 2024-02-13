package com.aditumcr.todoapp.usecases;

import com.aditumcr.todoapp.domain.Status;
import com.aditumcr.todoapp.domain.Todo;

import java.util.Objects;

public record TodoDTO(String id, String description, Status status) {
    public static TodoDTO fromAggregate(Todo todo) {
        return new TodoDTO(
                todo.getId().getValue(),
                todo.getDescription().value(),
                todo.getStatus().value()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoDTO todoDTO = (TodoDTO) o;
        return Objects.equals(id, todoDTO.id) && Objects.equals(description, todoDTO.description) && status == todoDTO.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, status);
    }
}
