package com.example.uade.tpo.demo.exceptions;

public class PedidoNotFoundException extends Exception {
    public PedidoNotFoundException() {
        super("Pedido not found");
    }
}