package com.codestates.justDoIt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class TodoDto {

    @Getter
//    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post {
        @NotBlank
        private String title;
        private long order;
        private boolean completed;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long id;
        private String title;
        private long todo_order;
        private boolean completed;

        public void setId(long toDoId) {this.id = toDoId;}
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long id;
        private String title;
        private long todo_order;
        private boolean completed;
    }

}
