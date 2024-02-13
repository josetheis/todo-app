package com.aditumcr.todoapp.usecases.find;

import com.aditumcr.todoapp.domain.TodoRepository;
import com.aditumcr.todoapp.domain.errors.TodoNotFoundException;
import com.aditumcr.todoapp.domain.value_objects.TodoId;
import com.aditumcr.todoapp.usecases.TodoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoFinderById {
    @Autowired
    private TodoRepository todoRepositoryImpl;

    public TodoDTO execute(TodoId id) {
        return todoRepositoryImpl.findById(id)
                .map(TodoDTO::fromAggregate)
                .orElseThrow(TodoNotFoundException::new);
    }
}
