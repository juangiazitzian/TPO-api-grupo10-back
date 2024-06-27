package com.example.uade.tpo.demo.exceptions;

public class DescuentoUsedException extends Exception {
    public DescuentoUsedException(){
        super("Descuento Already used by user");
    }
}
