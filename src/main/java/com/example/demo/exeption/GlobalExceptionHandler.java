package com.example.demo.exeption;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DbResourceNotFound.class)
    public ResponseEntity<String> handleNotFound(DbResourceNotFound e) {
        return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
    }
}
