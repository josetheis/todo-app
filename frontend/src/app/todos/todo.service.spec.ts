import { TestBed } from '@angular/core/testing';

import { TodoService } from './todo.service';
import { of } from 'rxjs';
import CreateTodoDTO from './interfaces/createTodoDTO';
import Todo from './interfaces/todo';
import TodoStatus from './enums/todoStatus';
import { v4 } from 'uuid';
import UpdateTodoDescriptionDTO from './interfaces/UpdateTodoDescriptionDTO';

describe('TodoService', () => {
  let service: TodoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TodoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return an Observable of Todo[]', () => {
    const todos: Todo[] = [
      { id: '1', description: 'Todo 1', status: TodoStatus.COMPLETED },
      { id: '2', description: 'Todo 2', status: TodoStatus.PENDING }
    ];
    spyOn(service['http'], 'get').and.returnValue(of(todos));

    service.getTodos().subscribe((result) => {
      expect(result).toEqual(todos);
    });
  });

  it('should return an Observable of void when createTodo() is called with a valid CreateTodoDTO', () => {
    const dto: CreateTodoDTO = { id: v4(), description: 'New Todo' };
    spyOn(service['http'], 'post').and.returnValue(of());

    service.createTodo(dto).subscribe(() => {
      expect().nothing();
    });
  });

  it('should return an Observable of void when createTodo() is called with a valid CreateTodoDTO', () => {
    const dto: CreateTodoDTO = { id: v4(), description: 'New Todo' };
    spyOn(service['http'], 'post').and.returnValue(of());

    service.createTodo(dto).subscribe(() => {
      expect().nothing();
    });
  });

  it('should return an Observable of void when updateTodoDescription() is called with a valid id and UpdateTodoDescriptionDTO', () => {
    const id = '1';
    const dto: UpdateTodoDescriptionDTO = { value: 'Updated Todo Description' };
    spyOn(service['http'], 'patch').and.returnValue(of());

    service.updateTodoDescription(id, dto).subscribe(() => {
      expect().nothing();
    });
  });

  it('should return an Observable of Todo[] with the correct number of todos', () => {
    const todos: Todo[] = [
      { id: '1', description: 'Todo 1', status: TodoStatus.COMPLETED },
      { id: '2', description: 'Todo 2', status: TodoStatus.PENDING }
    ];
    spyOn(service['http'], 'get').and.returnValue(of(todos));

    service.getTodos().subscribe((result) => {
      expect(result.length).toBe(todos.length);
    });
  });
});
