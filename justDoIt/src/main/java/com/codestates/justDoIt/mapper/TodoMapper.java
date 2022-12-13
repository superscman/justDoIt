package com.codestates.justDoIt.mapper;

import com.codestates.justDoIt.dto.TodoDto;
import com.codestates.justDoIt.entity.Todo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    default Todo todoPostDtoToTodo(TodoDto.Post requestBody){
        Todo todo = new Todo();
        todo.setTodo_order(requestBody.getOrder());
        todo.setTitle(requestBody.getTitle());
        todo.setCompleted(requestBody.isCompleted());
        return todo;
    };
    Todo todoPatchDtoToTodo(TodoDto.Patch requestBody);
    TodoDto.Response todoToTodoResponse(Todo todo);
    List<TodoDto.Response> todosToTodoResponse(List<Todo> todos);
}
