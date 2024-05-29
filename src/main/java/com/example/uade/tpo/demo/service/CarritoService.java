package com.example.uade.tpo.demo.service;

import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.uade.tpo.demo.entity.Carrito;

public interface CarritoService {
    Page<Carrito> getCarritos(PageRequest pageRequest);

    Optional<Carrito> getCarritoById(Long id);

    Carrito newCarrito(Map<Integer, Integer> cart);
}