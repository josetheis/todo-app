package com.aditumcr.todoapp.usecases.update;

import com.aditumcr.todoapp.domain.Todo;
import com.aditumcr.todoapp.domain.TodoRepository;
import com.aditumcr.todoapp.domain.errors.TodoNotFoundException;
import com.aditumcr.todoapp.domain.value_objects.TodoId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class TodoUpdater {
    protected final TodoRepository todoRepositoryImpl;

    public Todo findById(TodoId id) {
        return todoRepositoryImpl.findById(id).orElseThrow(TodoNotFoundException::new);
    }
}
