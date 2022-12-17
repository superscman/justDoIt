package com.codestates.justDoIt.controller;

import com.codestates.justDoIt.mapper.TodoMapper;
import com.codestates.justDoIt.dto.TodoDto;
import com.codestates.justDoIt.entity.Todo;
import com.codestates.justDoIt.response.SingleResponseDto;
import com.codestates.justDoIt.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://todobackend.com")
@Validated
@Slf4j
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;
    private final TodoMapper mapper;

    @PostMapping
    public ResponseEntity postTodo(@Valid @RequestBody TodoDto.Request post) {
        Todo todo = todoService.createTodo(mapper.todoRequestToTodo(post));
        return new ResponseEntity<>(mapper.todoToTodoDtoResponse(todo), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchTodo(@PathVariable("id") @Positive long id,
                                    @RequestBody TodoDto.Request patch){

        Todo todo = todoService.updateTodo(id, patch);

        return new ResponseEntity<>(mapper.todoToTodoDtoResponse(todo),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getTodo(@PathVariable("id") @Positive long id) {
        Todo todo = todoService.findTodo(id);

        return new ResponseEntity<>(mapper.todoToTodoDtoResponse(todo),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity getTodos() {
        List<Todo> todos = todoService.findTodos();
        return new ResponseEntity<>(mapper.todosToTodoDtoResponse(todos),HttpStatus.OK);
    }

//    @PostMapping("/redirect") // 일반 리다이렉션 겟으로 받는다.
//    public ResponseEntity<?> keepRedirect() {
//        HttpHeaders headers = new HttpHeaders();
//        log.info("###############postmaaping redirect to do HI");
//        headers.setLocation(URI.create("/"));
//
//        return new ResponseEntity<>(headers, HttpStatus.PERMANENT_REDIRECT);
//    }

//    @GetMapping("/redirect") // 일반 리다이렉션 겟으로 받는다.
//    public ResponseEntity<?> redirect() {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        log.info("##################getmapping redirect Hi");
//        httpHeaders.setLocation(URI.create("/"));
//        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
//    }

//    @GetMapping
//    public ResponseEntity getTodos(@Positive @RequestParam int page,
//                                   @Positive @RequestParam int size) {
//        Page<Todo> todoPage = todoService.findTodos(page-1,size);
//        List<Todo> todos = todoPage.getContent();
//
//        return new ResponseEntity<>(
//                new MultiResponseDto<>(mapper.todosToTodoResponse(todos),todoPage),HttpStatus.OK);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodo(@PathVariable("id") @Positive long id) {
        todoService.deleteTodo(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity deleteTodos() {
        todoService.deleteTodoAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
