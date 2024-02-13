package com.aditumcr.todoapp.infrastructure.controllers;

import com.aditumcr.todoapp.domain.value_objects.TodoDescription;
import com.aditumcr.todoapp.domain.value_objects.TodoId;
import com.aditumcr.todoapp.usecases.create.TodoCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class TodoPostController extends BaseTodoController {
    @Autowired
    private TodoCreator todoCreator;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Request request) {
        todoCreator.execute(
                new TodoId(request.id()),
                new TodoDescription(request.description())
        );
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(request.id())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    private record Request(
            String id,
            String description
    ) {
    }
}
