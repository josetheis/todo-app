import TodoStatus from "../enums/todoStatus";

export default interface Todo {
    id: string;
    description: string;
    status: TodoStatus;
}