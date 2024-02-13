package com.aditumcr.todoapp.usecases.delete;

import com.aditumcr.todoapp.domain.TodoRepository;
import com.aditumcr.todoapp.domain.errors.TodoNotFoundException;
import com.aditumcr.todoapp.domain.value_objects.TodoId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoRemover {
    @Autowired
    private TodoRepository todoRepositoryImpl;

    public void execute(TodoId id) {
        todoRepositoryImpl.findById(id).ifPresentOrElse(todo -> {
            todoRepositoryImpl.deleteById(id);
        }, () -> {
            throw new TodoNotFoundException();
        });
    }
}
