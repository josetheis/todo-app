import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import TodoFormPayload from '../../interfaces/todoFormPayload';

@Component({
  selector: 'app-create-todo-form',
  templateUrl: './create-todo-form.component.html',
  styleUrl: './create-todo-form.component.css'
})
export class CreateTodoFormComponent {
  @Output() create = new EventEmitter<TodoFormPayload>();
  form: FormGroup = this.fb.group({
    description: ['', Validators.required]
  });

  constructor(private fb: FormBuilder) { }

  public createTodo() {
    if (this.form.valid) {
      this.create.emit(this.form.value);
      this.form.reset();
    }
  }
}
