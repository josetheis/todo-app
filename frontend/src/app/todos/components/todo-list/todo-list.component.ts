import { Component, EventEmitter, Input, Output } from '@angular/core';
import Todo from '../../interfaces/todo';
import TodoListAction from '../../interfaces/todoListAction';
import TodoListActionType from '../../interfaces/todoListActionType';
import TodoStatus from '../../enums/todoStatus';

@Component({
  selector: 'app-todo-list',
  templateUrl: `./todo-list.component.html`,
  styleUrl: './todo-list.component.css',
})
export class TodoListComponent {
  @Input() todos: Todo[] = [];
  @Output() action: EventEmitter<TodoListAction> = new EventEmitter<TodoListAction>();

  public toggleTodo = (id: string) => {
    this.action.emit({ id, action: TodoListActionType.TOGGLE_STATUS });
  }

  public updateTodo = (id: string) => {
    this.action.emit({ id, action: TodoListActionType.UPDATE });
  }

  public deleteTodo = (id: string) => {
    this.action.emit({ id, action: TodoListActionType.DELETE });
  }

  public isCompleted(status: TodoStatus) {
    return status === TodoStatus.COMPLETED;
  }
}
