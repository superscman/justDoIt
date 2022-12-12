package com.codestates.justDoIt.exception;

import lombok.Getter;

public enum ExceptionCode {
    TODO_EXISTS( 409, "Todo exists"),
    TODO_NOT_FOUND(404, "Todo not found");

    @Getter
    private int status;

    @Getter
    private String messgage;

    ExceptionCode(int status, String messgage) {
        this.status = status;
        this.messgage = messgage;
    }
}
