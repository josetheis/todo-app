package com.aditumcr.todoapp.usecases.find;

import com.aditumcr.todoapp.domain.Status;
import com.aditumcr.todoapp.domain.Todo;
import com.aditumcr.todoapp.domain.TodoRepository;
import com.aditumcr.todoapp.domain.value_objects.TodoDescription;
import com.aditumcr.todoapp.domain.value_objects.TodoId;
import com.aditumcr.todoapp.domain.value_objects.TodoStatus;
import com.aditumcr.todoapp.usecases.TodoDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoFinderTests {
    @Mock
    private TodoRepository repository;
    @InjectMocks
    private TodoFinder finder;

    @Test
    void should_find_all_todos() {
        List<TodoDTO> expectedTodos = List.of(
                new TodoDTO(
                        "ec9e700b-0c73-4ad8-be0e-abc3e7264924",
                        "Helloo",
                        Status.PENDING
                )
        );

        when(repository.findAll()).thenReturn(List.of(
                new Todo(
                        new TodoId("ec9e700b-0c73-4ad8-be0e-abc3e7264924"),
                        new TodoDescription("Helloo"),
                        new TodoStatus(Status.PENDING)
                )
        ));

        List<TodoDTO> actualTodos = finder.execute();
        assertEquals(expectedTodos, actualTodos);
        verify(repository).findAll();
    }
}