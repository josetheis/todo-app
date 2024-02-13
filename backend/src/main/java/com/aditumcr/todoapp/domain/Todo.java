package com.aditumcr.todoapp.domain;

import com.aditumcr.todoapp.domain.value_objects.TodoDescription;
import com.aditumcr.todoapp.domain.value_objects.TodoId;
import com.aditumcr.todoapp.domain.value_objects.TodoStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
public class Todo {
    private final TodoId id;
    @Setter private TodoDescription description;
    @Setter private TodoStatus status;

    public Todo(TodoId id, TodoDescription description, TodoStatus status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return Objects.equals(id, todo.id) && Objects.equals(description, todo.description) && Objects.equals(status, todo.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, status);
    }
}
