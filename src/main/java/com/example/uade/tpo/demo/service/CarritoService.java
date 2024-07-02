package com.example.uade.tpo.demo.service;

import java.util.Optional;

import com.example.uade.tpo.demo.entity.Carrito;

public interface CarritoService {

    Optional<Carrito> getCarritoById(Long id);
    
    Optional<Carrito> getCarritoByCuentaId(Long id);

    Long newCarrito();
    
    double totalCarrito(Long carritoId);

}
