package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleValidationViolation(Exception e) {
        Error error = Error.builder()
                .message(e.getMessage())
                .status(400)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Error> handleUserNotFound(DataNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getError());
    }

    @ExceptionHandler(NoGroupException.class)
    public ResponseEntity<Error> handleNoGroupException(NoGroupException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getError());
    }
}
