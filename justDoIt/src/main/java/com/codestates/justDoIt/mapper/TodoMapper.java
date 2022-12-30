package com.codestates.justDoIt.mapper;

import com.codestates.justDoIt.dto.TodoDto;
import com.codestates.justDoIt.entity.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class TodoMapper {
    public Todo todoRequestToTodo(TodoDto.Request request) {
        if(request == null) {return null;} //null 값 처리하는 것을 잊지말자

        if(ObjectUtils.isEmpty(request.getTitle())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if(ObjectUtils.isEmpty(request.getOrder())) {
            request.setOrder(0L);
        }

        if(ObjectUtils.isEmpty(request.isCompleted())) {
            request.setCompleted(false);
        }

        Todo todo = Todo
                .builder()
                .title(request.getTitle())
                .order(request.getOrder())
                .completed(request.isCompleted())
                .build();//빌더를 쓰는연습을 해야됩니다.

        return todo;
    }
    public TodoDto.Response todoToTodoDtoResponse(Todo todo) {
        if (todo == null) {
            return null;
        }

        TodoDto.Response response = TodoDto.Response
                .builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .order(todo.getOrder())
                .completed(todo.isCompleted())
                .url("http://localhost:8080/"+todo.getId())
                .build();
        return response;
    }

    public List<TodoDto.Response> todosToTodoDtoResponse(List<Todo> todos) {
        if(todos == null) { return null;}

        List<TodoDto.Response> list = new ArrayList<>(todos.size());
        for (Todo todo : todos) {
            list.add(todoToTodoDtoResponse(todo));
        }
        return list;
    }
}
