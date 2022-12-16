package com.codestates.justDoIt.controller;

import com.codestates.justDoIt.mapper.TodoMapper;
import com.codestates.justDoIt.dto.TodoDto;
import com.codestates.justDoIt.entity.Todo;
import com.codestates.justDoIt.response.SingleResponseDto;
import com.codestates.justDoIt.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://todobackend.com")
@Validated
@Slf4j
public class TodoController {
    private final TodoService todoService;
    private final TodoMapper mapper;

    public TodoController(TodoService toDoService, TodoMapper mapper) {
        this.todoService = toDoService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postTodo(@Valid @RequestBody TodoDto.Post post) {
        Todo todo = todoService.createTodo(mapper.todoPostDtoToTodo(post));
        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchTodo(@PathVariable("id") @Positive long id,
                                    @RequestBody TodoDto.Patch patch){
        patch.setId(id);
        Todo todo = todoService.updateTodo(mapper.todoPatchDtoToTodo(patch));

        return new ResponseEntity<>(mapper.todoToTodoResponse(todo),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getTodo(@PathVariable("id") @Positive long id) {
        Todo todo = todoService.findTodo(id);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.todoToTodoResponse(todo)),HttpStatus.OK);
    }
    @GetMapping
    public String getTodos() {
//        List<Todo> todos = todoService.findTodos();
        return "Todo Application";
//        return new ResponseEntity<>(todos,HttpStatus.CREATED);
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
