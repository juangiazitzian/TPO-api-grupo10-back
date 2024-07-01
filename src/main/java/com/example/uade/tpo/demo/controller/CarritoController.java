package com.example.uade.tpo.demo.controller;

import com.example.uade.tpo.demo.entity.Carrito;
import com.example.uade.tpo.demo.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping("/id/{id}")
    public ResponseEntity<Carrito> getCarritoById(@PathVariable Long id) {
        Optional<Carrito> carrito = carritoService.getCarritoById(id);
        return carrito.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/cuenta-id/{id}")
    public ResponseEntity<Carrito> getCarritoByCuentaId(@PathVariable Long id) {
        Optional<Carrito> carrito = carritoService.getCarritoByCuentaId(id);
        return carrito.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
