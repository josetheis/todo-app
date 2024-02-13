package com.aditumcr.todoapp.usecases.create;

import com.aditumcr.todoapp.domain.Status;
import com.aditumcr.todoapp.domain.Todo;
import com.aditumcr.todoapp.domain.TodoRepository;
import com.aditumcr.todoapp.domain.errors.TodoIdInvalidException;
import com.aditumcr.todoapp.domain.errors.TodoDescriptionEmptyException;
import com.aditumcr.todoapp.domain.value_objects.TodoDescription;
import com.aditumcr.todoapp.domain.value_objects.TodoId;
import com.aditumcr.todoapp.domain.value_objects.TodoStatus;
import com.aditumcr.todoapp.domain.errors.TodoAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
final class TodoCreatorTests {
    @Mock private TodoRepository repository;
    @InjectMocks private TodoCreator todoCreator;
    private final Todo todo = new Todo(
            new TodoId("ec9e700b-0c73-4ad8-be0e-abc3e7264924"),
            new TodoDescription("Hello world"),
            new TodoStatus(Status.PENDING)
    );

    @Test
    void save_a_valid_todo() {
        todoCreator.execute(todo.getId(), todo.getDescription());
        verify(repository, atLeastOnce()).save(todo);
    }

    @Test
    void throw_error_if_todo_already_exists() {
        when(repository.findById(todo.getId())).thenReturn(Optional.of(todo));
        assertThatThrownBy(() -> todoCreator.execute(todo.getId(), todo.getDescription()))
                .isInstanceOf(TodoAlreadyExistsException.class)
                .hasMessage("La tarea con ID: " + todo.getId().getValue() + " ya existe.");
    }

    @Test
    void should_fail_if_id_is_invalid_uuid() {
        String invalidId = "invalid";
        assertThatThrownBy(() -> todoCreator.execute(new TodoId(invalidId), todo.getDescription()))
                .isInstanceOf(TodoIdInvalidException.class)
                .hasMessage("El ID: " + invalidId + " no es un UUID valido.");
    }

    @Test
    void should_fail_if_description_is_an_empty_string() {
        assertThatThrownBy(() -> todoCreator.execute(todo.getId(), new TodoDescription("")))
                .isInstanceOf(TodoDescriptionEmptyException.class)
                .hasMessage("La descripcion de un Todo no puede ser null o vacio");
    }

    @Test
    void should_fail_if_description_is_null() {
        assertThatThrownBy(() -> todoCreator.execute(todo.getId(), new TodoDescription(null)))
                .isInstanceOf(TodoDescriptionEmptyException.class)
                .hasMessage("La descripcion de un Todo no puede ser null o vacio");
    }

    @Test
    void should_fail_if_description_is_blank() {
        assertThatThrownBy(() -> todoCreator.execute(todo.getId(), new TodoDescription(" ")))
                .isInstanceOf(TodoDescriptionEmptyException.class)
                .hasMessage("La descripcion de un Todo no puede ser null o vacio");
    }
}