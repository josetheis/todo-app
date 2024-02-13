import { Component, Input } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-edit-todo-modal',
  templateUrl: './edit-todo-modal.component.html',
  styleUrl: './edit-todo-modal.component.css',
})
export class EditTodoModalComponent {
  @Input() set prevDescription(value: string) {
    this.form.setValue({ description: value });
  }
  form = this.fb.group({
    description: ['', Validators.required],
  });

  constructor(private fb: FormBuilder, public modal: NgbActiveModal) {}

  public submit() {
    if (this.form.valid) {
      this.modal.close(this.form.value.description);
    }
  }
}
