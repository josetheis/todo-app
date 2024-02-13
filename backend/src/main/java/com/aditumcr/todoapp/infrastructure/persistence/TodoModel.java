package com.aditumcr.todoapp.infrastructure.persistence;

import com.aditumcr.todoapp.domain.*;
import com.aditumcr.todoapp.domain.value_objects.TodoDescription;
import com.aditumcr.todoapp.domain.value_objects.TodoId;
import com.aditumcr.todoapp.domain.value_objects.TodoStatus;
import com.aditumcr.todoapp.domain.errors.TodoIdInvalidException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "todo")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TodoModel {
    @Id private String id;
    private String description;
    @Enumerated(EnumType.STRING) private Status status;

    public Todo toDomain() {
        try {
            return new Todo(
                    new TodoId(id),
                    new TodoDescription(description),
                    new TodoStatus(status)
            );
        } catch (TodoIdInvalidException e) {
            throw new RuntimeException(e);
        }
    }

    public static TodoModel fromDomain(Todo todo) {
        return new TodoModel(
                todo.getId().getValue(),
                todo.getDescription().value(),
                todo.getStatus().value()
        );
    }
}
