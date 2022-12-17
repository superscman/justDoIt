package com.codestates.justDoIt.service;

import com.codestates.justDoIt.dto.TodoDto;
import com.codestates.justDoIt.entity.Todo;
import com.codestates.justDoIt.repository.ToDoReopository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final ToDoReopository toDoReopository;

    public TodoService(ToDoReopository toDoReopository) {
        this.toDoReopository = toDoReopository;
    }

    public Todo createTodo(Todo todo) {
        return toDoReopository.save(todo);
    }

    public Todo updateTodo(long todoId, TodoDto.Request request) {
        Todo findTodo = findVerifiedTodo(todoId);

        Optional.ofNullable(request.getTitle())
                .ifPresent(title -> findTodo.setTitle(title));
        Optional.ofNullable(request.getOrder())
                .ifPresent(order -> findTodo.setOrder(order));
        Optional.ofNullable((request.isCompleted()))
                .ifPresent(completed -> findTodo.setCompleted(completed));

        return toDoReopository.save(findTodo);
    }

    public Todo findTodo(long todoId) {return findVerifiedTodo(todoId);}

    public List<Todo> findTodos() {
        return toDoReopository.findAll();
    }

//    public Page<Todo> findTodos(int page, int size) {
//        return toDoReopository.findAll(PageRequest.of(page,size, Sort.by("id").descending()));
//    }

    public void deleteTodo(long todoId) {
        Todo findTodo = findVerifiedTodo(todoId);

        toDoReopository.delete(findTodo);
    }

    public void deleteTodoAll() {
        toDoReopository.deleteAll();
    }

    public Todo findVerifiedTodo(long todoId) {
        Optional<Todo> optionalTodo = toDoReopository.findById(todoId);

        Todo findTodo = optionalTodo.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return findTodo;
    }

//    private void verifyExistsTodo(String title) {
//        Optional<Todo> todo = toDoReopository.findByTitle(title);
//        if(todo.isPresent())
//            throw new BusinessLogicException(ExceptionCode.TODO_EXISTS);
//    }
}
