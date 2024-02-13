import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { TodoListComponent } from './components/todo-list/todo-list.component';
import { TodoService } from './todo.service';
import { CreateTodoFormComponent } from './components/create-todo-form/create-todo-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TodosComponent } from './components/todos/todos.component';
import { SharedModule } from '../shared/shared.module';
import { EditTodoModalComponent } from './components/edit-todo-modal/edit-todo-modal.component';



@NgModule({
  declarations: [
    TodoListComponent,
    CreateTodoFormComponent,
    TodosComponent,
    EditTodoModalComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule
  ],
  exports: [
    TodoListComponent,
    CreateTodoFormComponent,
    TodosComponent
  ],
  providers: [
    TodoService
  ]
})
export class TodosModule { }
