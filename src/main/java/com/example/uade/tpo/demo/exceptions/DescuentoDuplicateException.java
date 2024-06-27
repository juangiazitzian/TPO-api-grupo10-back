package com.example.uade.tpo.demo.exceptions;

public class DescuentoDuplicateException extends Exception {
    public DescuentoDuplicateException() {
        super("Code already exists");
    }
}