import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, EventEmitter, Input, Output } from '@angular/core';
import Todo from '../../interfaces/todo';
import TodoStatus from '../../enums/todoStatus';
import TodoListAction from '../../interfaces/todoListAction';

@Component({
  selector: 'app-todos',
  templateUrl: './todos.component.html',
  styleUrl: './todos.component.css'
})
export class TodosComponent {
  @Output() action = new EventEmitter<TodoListAction>();
  @Input() set todos(todos: Todo[]) {
    this.pendingTodos = todos.filter((todo) => todo.status === TodoStatus.PENDING);
    this.completedTodos = todos.filter((todo) => todo.status === TodoStatus.COMPLETED);
  }
  pendingTodos: Todo[] = [];
  completedTodos: Todo[] = [];

  handleAction(action: TodoListAction) {
    this.action.emit(action);
  }
}
