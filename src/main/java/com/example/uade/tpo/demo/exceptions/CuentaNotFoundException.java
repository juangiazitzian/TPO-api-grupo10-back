package com.example.uade.tpo.demo.exceptions;

public class CuentaNotFoundException extends Exception {
    public CuentaNotFoundException() {
        super("Account not found");
    }
}