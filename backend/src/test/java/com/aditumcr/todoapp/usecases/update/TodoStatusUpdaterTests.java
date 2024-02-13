package com.aditumcr.todoapp.usecases.update;

import com.aditumcr.todoapp.domain.Status;
import com.aditumcr.todoapp.domain.Todo;
import com.aditumcr.todoapp.domain.TodoRepository;
import com.aditumcr.todoapp.domain.errors.TodoStatusInvalidException;
import com.aditumcr.todoapp.domain.errors.TodoNotFoundException;
import com.aditumcr.todoapp.domain.value_objects.TodoDescription;
import com.aditumcr.todoapp.domain.value_objects.TodoId;
import com.aditumcr.todoapp.domain.value_objects.TodoStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoStatusUpdaterTests {
    @Mock
    private TodoRepository repository;
    @InjectMocks
    private TodoStatusUpdater updater;

    private final Todo todo = new Todo(
            new TodoId("ec9e700b-0c73-4ad8-be0e-abc3e7264924"),
            new TodoDescription("Hello world"),
            new TodoStatus(Status.PENDING)
    );

    @Test
    void should_update_valid_description() {
        when(repository.findById(todo.getId())).thenReturn(Optional.of(todo));
        updater.execute(todo.getId(), new TodoStatus(Status.PENDING));
        verify(repository, atLeastOnce()).save(todo);
    }

    @Test
    void should_throw_error_if_todo_does_not_exists() {
        when(repository.findById(todo.getId()))
                .thenReturn(Optional.empty());
        assertThatThrownBy(() -> updater.execute(todo.getId(), todo.getStatus()))
                .isInstanceOf(TodoNotFoundException.class)
                .hasMessage("La tarea no pudo ser encontrada");
    }

    @Test
    void should_throw_error_if_new_description_is_invalid() {
        assertThatThrownBy(() -> updater.execute(todo.getId(), new TodoStatus("invalid")))
                .isInstanceOf(TodoStatusInvalidException.class)
                .hasMessage("El valor de status no es valido");
    }
}