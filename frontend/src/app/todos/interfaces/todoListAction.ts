import TodoListActionType from "./todoListActionType";

export default interface TodoListAction {
    id: string;
    action: TodoListActionType;
}