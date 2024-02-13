package com.aditumcr.todoapp.infrastructure.controllers;

import com.aditumcr.todoapp.domain.value_objects.TodoId;
import com.aditumcr.todoapp.usecases.TodoDTO;
import com.aditumcr.todoapp.usecases.find.TodoFinderById;
import com.aditumcr.todoapp.usecases.find.TodoFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoGetController extends BaseTodoController {
    @Autowired
    private TodoFinder finder;
    @Autowired
    private TodoFinderById findTodoById;

    @GetMapping
    public List<TodoDTO> index() {
        return finder.execute();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDTO> getById(@PathVariable String id) {
        TodoId todoId = new TodoId(id);
        return ResponseEntity.ok(findTodoById.execute(todoId));
    }
}
