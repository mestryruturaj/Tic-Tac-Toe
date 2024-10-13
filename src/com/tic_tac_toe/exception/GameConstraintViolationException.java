package com.tic_tac_toe.exception;

public class GameConstraintViolationException extends RuntimeException {
    public GameConstraintViolationException(String message) {
        super(message);
    }

}
