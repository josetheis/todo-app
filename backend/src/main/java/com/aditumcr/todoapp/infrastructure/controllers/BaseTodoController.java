package com.aditumcr.todoapp.infrastructure.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/todos")
@CrossOrigin(origins = "http://localhost:4200")
public abstract class BaseTodoController { }
