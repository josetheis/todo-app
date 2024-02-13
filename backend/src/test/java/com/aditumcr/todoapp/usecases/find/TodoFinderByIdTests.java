package com.aditumcr.todoapp.usecases.find;

import com.aditumcr.todoapp.domain.Status;
import com.aditumcr.todoapp.domain.Todo;
import com.aditumcr.todoapp.domain.TodoRepository;
import com.aditumcr.todoapp.domain.errors.TodoNotFoundException;
import com.aditumcr.todoapp.domain.value_objects.TodoDescription;
import com.aditumcr.todoapp.domain.value_objects.TodoId;
import com.aditumcr.todoapp.domain.value_objects.TodoStatus;
import com.aditumcr.todoapp.usecases.TodoDTO;
import com.aditumcr.todoapp.usecases.update.TodoDescriptionUpdater;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoFinderByIdTests {
    @Mock
    private TodoRepository repository;
    @InjectMocks
    private TodoFinderById finder;

    private final Todo todo = new Todo(
            new TodoId("ec9e700b-0c73-4ad8-be0e-abc3e7264924"),
            new TodoDescription("Hello world"),
            new TodoStatus(Status.PENDING)
    );

    @Test
    void should_find_todo_if_it_exists() {
        when(repository.findById(todo.getId())).thenReturn(Optional.of(todo));
        TodoDTO expectedTodo = TodoDTO.fromAggregate(todo);
        TodoDTO actualTodo = finder.execute(todo.getId());
        assertEquals(expectedTodo, actualTodo);
    }

    @Test
    void should_throw_error_if_todo_does_not_exists() {
        when(repository.findById(todo.getId())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> finder.execute(todo.getId()))
                .isInstanceOf(TodoNotFoundException.class)
                .hasMessage("La tarea no pudo ser encontrada");
    }
}