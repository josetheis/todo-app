package com.aditumcr.todoapp.usecases.create;

import com.aditumcr.todoapp.domain.*;
import com.aditumcr.todoapp.domain.value_objects.TodoDescription;
import com.aditumcr.todoapp.domain.value_objects.TodoId;
import com.aditumcr.todoapp.domain.value_objects.TodoStatus;
import com.aditumcr.todoapp.domain.errors.TodoAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoCreator {
    @Autowired
    private TodoRepository todoRepositoryImpl;

    public void execute(TodoId id, TodoDescription description) {
        todoRepositoryImpl.findById(id)
                .ifPresentOrElse(todo -> {
                    throw new TodoAlreadyExistsException(todo.getId());
                }, () -> {
                    Todo todo = new Todo(id, description, new TodoStatus(Status.PENDING));
                    todoRepositoryImpl.save(todo);
                });
    }
}
