package com.example.demo.exeption;

public class DbResourceNotFound extends RuntimeException{
    public DbResourceNotFound(String message) {
        super(message);
    }
}
