package com.aditumcr.todoapp.domain;

import com.aditumcr.todoapp.domain.value_objects.TodoId;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {
    public List<Todo> findAll();
    public Optional<Todo> findById(TodoId id);

    public void save(Todo todo);
    public void deleteById(TodoId id);
}
