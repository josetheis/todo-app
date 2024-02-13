package com.aditumcr.todoapp.usecases.update;

import com.aditumcr.todoapp.domain.Todo;
import com.aditumcr.todoapp.domain.TodoRepository;
import com.aditumcr.todoapp.domain.value_objects.TodoDescription;
import com.aditumcr.todoapp.domain.value_objects.TodoId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoDescriptionUpdater extends TodoUpdater {
    @Autowired
    public TodoDescriptionUpdater(TodoRepository todoRepositoryImpl) {
        super(todoRepositoryImpl);
    }

    public void execute(TodoId id, TodoDescription description) {
        Todo todo = findById(id);
        todo.setDescription(description);
        todoRepositoryImpl.save(todo);
    }
}
