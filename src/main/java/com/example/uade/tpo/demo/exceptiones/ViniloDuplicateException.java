package com.example.uade.tpo.demo.exceptiones;

public class ViniloDuplicateException extends Exception {
    public ViniloDuplicateException() {
        super("Vinyl with the same title already exists");
    }
}
