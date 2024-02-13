package com.aditumcr.todoapp.usecases.delete;

import com.aditumcr.todoapp.domain.Status;
import com.aditumcr.todoapp.domain.Todo;
import com.aditumcr.todoapp.domain.TodoRepository;
import com.aditumcr.todoapp.domain.errors.TodoIdInvalidException;
import com.aditumcr.todoapp.domain.errors.TodoNotFoundException;
import com.aditumcr.todoapp.domain.value_objects.TodoDescription;
import com.aditumcr.todoapp.domain.value_objects.TodoId;
import com.aditumcr.todoapp.domain.value_objects.TodoStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoRemoverTests {
    @Mock
    private TodoRepository repository;
    @InjectMocks
    private TodoRemover remover;

    private final Todo todo = new Todo(
            new TodoId("ec9e700b-0c73-4ad8-be0e-abc3e7264924"),
            new TodoDescription("Hello world"),
            new TodoStatus(Status.PENDING)
    );

    @Test
    void should_delete_todo_if_it_exists() {
        remover.execute(todo.getId());
        verify(repository, atLeastOnce()).deleteById(todo.getId());
    }

    @Test
    void should_throw_error_if_todo_does_not_exists() {
        assertThatThrownBy(() -> remover.execute(todo.getId()))
                .isInstanceOf(TodoNotFoundException.class)
                .hasMessage("La tarea no pudo ser encontrada");
    }

    @Test
    void should_throw_error_if_id_is_not_valid() {
        String invalidId = "invalid";
        assertThatThrownBy(() -> remover.execute(new TodoId(invalidId)))
                .isInstanceOf(TodoIdInvalidException.class)
                .hasMessage("El ID: " + invalidId + " no es un UUID valido.");

    }
}