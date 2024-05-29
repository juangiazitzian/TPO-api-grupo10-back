package com.example.uade.tpo.demo.exceptions;

public class ViniloDuplicateException extends Exception {
    public ViniloDuplicateException() {
        super("Vinyl with the same title already exists");
    }
}