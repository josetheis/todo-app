package com.aditumcr.todoapp.usecases.update;

import com.aditumcr.todoapp.domain.Todo;
import com.aditumcr.todoapp.domain.value_objects.TodoId;
import com.aditumcr.todoapp.domain.TodoRepository;
import com.aditumcr.todoapp.domain.value_objects.TodoStatus;
import com.aditumcr.todoapp.domain.errors.TodoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoStatusUpdater extends TodoUpdater {
    @Autowired
    public TodoStatusUpdater(TodoRepository todoRepositoryImpl) {
        super(todoRepositoryImpl);
    }

    public void execute(TodoId id, TodoStatus status) {
        Todo todo = findById(id);
        todo.setStatus(status);
        todoRepositoryImpl.save(todo);
    }
}
