package com.codestates.justDoIt.service;

import com.codestates.justDoIt.entity.Todo;
import com.codestates.justDoIt.exception.BusinessLogicException;
import com.codestates.justDoIt.exception.ExceptionCode;
import com.codestates.justDoIt.repository.ToDoReopository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final ToDoReopository toDoReopository;

    public TodoService(ToDoReopository toDoReopository) {
        this.toDoReopository = toDoReopository;
    }

    public Todo createTodo(Todo todo) {
        verifyExistsTodo(todo.getTitle());

        return toDoReopository.save(todo);
    }

    public Todo updateTodo(Todo todo) {
        Todo findTodo = findVerifiedTodo(todo.getId());

        Optional.ofNullable(todo.getTitle())
                .ifPresent(name -> findTodo.setTitle(name));
        Optional.ofNullable(todo.getTodo_order())
                .ifPresent(todo_order -> findTodo.setTodo_order(todo_order));
        Optional.ofNullable((todo.isCompleted()))
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

        Todo findTodo = optionalTodo.orElseThrow(()->new BusinessLogicException(ExceptionCode.TODO_NOT_FOUND));
        return findTodo;
    }

    private void verifyExistsTodo(String title) {
        Optional<Todo> todo = toDoReopository.findByTitle(title);
        if(todo.isPresent())
            throw new BusinessLogicException(ExceptionCode.TODO_EXISTS);
    }
}
