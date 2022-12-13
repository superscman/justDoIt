//package com.codestates.justDoIt.mapper;
//
//import com.codestates.justDoIt.dto.TodoDto;
//import com.codestates.justDoIt.entity.Todo;
//import com.google.gson.Gson;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//
//import static org.hamcrest.Matchers.is;
//import static org.hamcrest.Matchers.startsWith;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private Gson gson;
//
//    @BeforeEach
//    public void init() {
//
//    }
//
//    @DisplayName("포스트를 일단 먼저 하자")
//    @Test
//    void postTodoTest() throws Exception {
//        // given
//        TodoDto.Post post = new TodoDto.Post("공부하기",2, true);
//
//        String content = gson.toJson(post);
//
//        // when
//        ResultActions actions =
//                mockMvc.perform(
//                        MockMvcRequestBuilders.post("/")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content)
//                );
//
//        // then
//        actions
//                .andExpect(status().isCreated())
//                .andExpect(header().string("Content-Type", is(startsWith("application/json"))))
//                .andExpect(jsonPath("$.title").value(post.getTitle()));
//    }
//
//    @Test
//    void redirectTodoTest() throws Exception {
//        // given
//        TodoDto.Post post = new TodoDto.Post("공부하기",2, true);
//
//        String content = gson.toJson(post);
//
//        // when
////        ResultActions actions =
////                mockMvc.perform(
////                        MockMvcRequestBuilders.get("/redirect")
////                                .accept(MediaType.APPLICATION_JSON)
////                                .contentType(MediaType.APPLICATION_JSON)
////                );
//        ResultActions actions =
//                mockMvc.perform(
//                        MockMvcRequestBuilders.post("/redirect")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content)
//                );
//
//        // then
//        actions
//                .andExpect(status().isCreated())
//                .andExpect(header().string("Content-Type", is(startsWith("application/json"))))
//                .andExpect(jsonPath("$.title").value(post.getTitle()))
//                .andExpect(redirectedUrl("/"));
//    }
//
//    @DisplayName("패치 값이 들어오나 보자")
//    @Test
//    public void patchTodo() {
//        // given
//
//        // when
//
//        // then
//
//
//    }
//}
