package com.example.uade.tpo.demo.controller;

import com.example.uade.tpo.demo.entity.Cuenta;
import com.example.uade.tpo.demo.entity.Pedido;
import com.example.uade.tpo.demo.service.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<Page<Pedido>> getPedidos(@RequestParam(defaultValue = "0") Integer page,
    												@RequestParam(defaultValue = "10") Integer size) {
        Page<Pedido> pedidos = pedidoService.getPedidos(PageRequest.of(page, size));
        return ResponseEntity.ok().body(pedidos);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.getPedidoById(id);
        return pedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/cuenta/{id}")
    public ResponseEntity<List<Pedido>> getPedidosByUserId(@PathVariable Long id) {
        List<Pedido> pedidos = pedidoService.getPedidosByUserId(id);
        if (pedidos.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(pedidos);
        }
    }

    @PostMapping("/add-pedido")
    public ResponseEntity<Pedido> createPedido(@RequestParam String cart, Cuenta cuenta, String date, boolean delivery, String adress,
			String deliveryDate, boolean entregado, double subtotal, double descuento, double total) {
        try {
            Pedido newPedido = pedidoService.newPedido(cart, cuenta, date, delivery, adress, deliveryDate, entregado, subtotal, descuento, total);
            return ResponseEntity.status(HttpStatus.CREATED).body(newPedido);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}