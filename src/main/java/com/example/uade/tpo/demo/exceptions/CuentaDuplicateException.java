package com.example.uade.tpo.demo.exceptions;

public class CuentaDuplicateException extends Exception {
    public CuentaDuplicateException() {
        super("Account with the same username already exists");
    }
}