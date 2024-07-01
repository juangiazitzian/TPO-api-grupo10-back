package com.example.uade.tpo.demo.exceptions;

public class ViniloOutOfStockException extends Exception {
    public ViniloOutOfStockException() {
        super("Vinilo out of stock.");
    }
}