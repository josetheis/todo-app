import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TodosModule } from './todos/todos.module';
import { TodoService } from './todos/todo.service';
import { AsyncPipe } from '@angular/common';
import { v4 as uuidv4, v4 } from 'uuid';
import Todo from './todos/interfaces/todo';
import TodoFormPayload from './todos/interfaces/todoFormPayload';
import TodoStatus from './todos/enums/todoStatus';
import { SharedModule } from './shared/shared.module';
import TodoListAction from './todos/interfaces/todoListAction';
import TodoListActionType from './todos/interfaces/todoListActionType';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { EditTodoModalComponent } from './todos/components/edit-todo-modal/edit-todo-modal.component';
import { flatMap, map, mergeMap } from 'rxjs';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    HttpClientModule,
    TodosModule,
    SharedModule
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  todos: Todo[] = [];
  private id = v4();

  constructor(private todoService: TodoService, private modalService: NgbModal) {}

  ngOnInit(): void {
    this.todoService.getTodos().subscribe((todos) => {
      this.todos = todos;
    });
  }

  public createTodo({ description }: TodoFormPayload) {
    this.todoService.createTodo({
      id: this.id,
      description,
    }).subscribe(() => {
      this.addTodoToList(this.id, description);
      this.resetId();
    });
  }

  private addTodoToList(id: string, description: string) {
    this.todos = [...this.todos, { id, description, status: TodoStatus.PENDING }];
  }

  private resetId() {
    this.id = v4();
  }

  public handleTodoListAction({ id, action }: TodoListAction) {
    switch (action) {
      case TodoListActionType.TOGGLE_STATUS:
        this.toggleTodoStatus(id);
        break;
      case TodoListActionType.UPDATE:
        this.updateTodoDescription(id);
        break;
      case TodoListActionType.DELETE:
        this.deleteTodo(id);
        break;
    }
  }

  updateTodoDescription(id: string) {
    const modalRef = this.modalService.open(EditTodoModalComponent);
    modalRef.componentInstance.prevDescription = this.todos.find((todo) => todo.id === id)?.description || '';
    
    modalRef.closed.pipe(
      mergeMap((value) => this.todoService.updateTodoDescription(id, { value }).pipe(
        map(() => value)
      ))
    ).subscribe((newDescription) => {
      const todo = this.todos.find((todo) => todo.id === id);
      if (todo) {
        todo.description = newDescription;
        this.todos = [...this.todos];
      }
    });
  }

  toggleTodoStatus(id: string) {
    const todo = this.todos.find((todo) => todo.id === id);
    if (todo) {
      const todoStatus = todo.status === TodoStatus.PENDING ? TodoStatus.COMPLETED : TodoStatus.PENDING;
      this.todoService.updateTodoStatus(id, { value: todoStatus }).subscribe(() => {
        todo.status = todoStatus;
        this.todos = [...this.todos];
      });
    }
  }

  deleteTodo(id: string) {
    this.todoService.deleteTodo(id).subscribe(() => {
      this.todos = this.todos.filter((todo) => todo.id !== id);
    });
  }
}
