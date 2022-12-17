package com.codestates.justDoIt.dto;

import lombok.*;


public class TodoDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String title;
        private long order;
        private boolean completed = false;
    }

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private long id;
        private String title;
        private long order;
        private boolean completed;
        private String url;
    }

}
