package com.aditumcr.todoapp.infrastructure.controllers;

import com.aditumcr.todoapp.domain.value_objects.TodoId;
import com.aditumcr.todoapp.domain.errors.TodoIdInvalidException;
import com.aditumcr.todoapp.usecases.delete.TodoRemover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoDeleteController extends BaseTodoController {
    @Autowired
    private TodoRemover remover;

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> index(@PathVariable String id) {
        remover.execute(new TodoId(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
