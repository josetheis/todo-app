package com.aditumcr.todoapp.infrastructure.controllers;

import com.aditumcr.todoapp.domain.value_objects.TodoDescription;
import com.aditumcr.todoapp.domain.value_objects.TodoId;
import com.aditumcr.todoapp.domain.value_objects.TodoStatus;
import com.aditumcr.todoapp.usecases.update.TodoDescriptionUpdater;
import com.aditumcr.todoapp.usecases.update.TodoStatusUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TodoPatchController extends BaseTodoController {
    @Autowired
    private TodoStatusUpdater todoStatusUpdater;
    @Autowired
    private TodoDescriptionUpdater todoDescriptionUpdater;

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable String id, @RequestBody Request request) {
        System.out.println(request.value);
        todoStatusUpdater.execute(new TodoId(id), new TodoStatus(request.value));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}/description")
    public ResponseEntity<String> updateDescription(@PathVariable String id, @RequestBody Request request) {
        todoDescriptionUpdater.execute(new TodoId(id), new TodoDescription(request.value));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private record Request(String value) {
    }
}
