package com.aditumcr.todoapp.infrastructure.persistence;

import com.aditumcr.todoapp.domain.Todo;
import com.aditumcr.todoapp.domain.value_objects.TodoId;
import com.aditumcr.todoapp.domain.TodoRepository;
import com.aditumcr.todoapp.infrastructure.persistence.TodoModel;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TodoRepositoryImpl implements TodoRepository {
    SimpleJpaRepository<TodoModel, Serializable> jpaRepository;

    public TodoRepositoryImpl(EntityManager entityManager) {
        jpaRepository = new SimpleJpaRepository<>(TodoModel.class, entityManager);
    }

    @Override
    public List<Todo> findAll() {
        return jpaRepository
                .findAll()
                .stream()
                .map(TodoModel::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Todo> findById(TodoId id) {
        return jpaRepository
                .findById(id.getValue())
                .map(TodoModel::toDomain);
    }

    @Override
    @Transactional
    public void save(Todo todo) {
        TodoModel todoModel = TodoModel.fromDomain(todo);
        jpaRepository.save(todoModel);
    }

    @Override
    @Transactional
    public void deleteById(TodoId id) {
        jpaRepository.deleteById(id.getValue());
    }
}
