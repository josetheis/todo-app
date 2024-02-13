package com.aditumcr.todoapp.usecases.find;

import com.aditumcr.todoapp.domain.TodoRepository;
import com.aditumcr.todoapp.usecases.TodoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoFinder {
    @Autowired private TodoRepository todoRepositoryImpl;

    public List<TodoDTO> execute() {
        return todoRepositoryImpl
                .findAll()
                .stream()
                .map(TodoDTO::fromAggregate)
                .collect(Collectors.toList());
    }
}
