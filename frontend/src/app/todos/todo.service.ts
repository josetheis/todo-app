import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import UpdateTodoDescriptionDTO from './interfaces/UpdateTodoDescriptionDTO';
import CreateTodoDTO from './interfaces/createTodoDTO';
import UpdateTodoStatusDTO from './interfaces/updateTodoStatusDTO';
import Todo from './interfaces/todo';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  private readonly url = 'http://localhost:8080/api/v1/todos';

  constructor(private http: HttpClient) { }

  getTodos(): Observable<Todo[]> {
    return this.http.get<Todo[]>(this.url);
  }

  createTodo(dto: CreateTodoDTO): Observable<void> {
    return this.http.post<void>(this.url, dto);
  }

  updateTodoDescription(id: string, dto: UpdateTodoDescriptionDTO): Observable<void> {
    return this.http.patch<void>(`${this.url}/${id}/description`, dto);
  }

  updateTodoStatus(id: string, dto: UpdateTodoStatusDTO): Observable<void> {
    return this.http.patch<void>(`${this.url}/${id}/status`, dto);
  }

  deleteTodo(id: string): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}
