package com.example.uade.tpo.demo.exceptions;

public class ViniloDuplicateException2 extends Exception {
    public ViniloDuplicateException2() {
        super("Vinyl with the same title already exists");
    }
}