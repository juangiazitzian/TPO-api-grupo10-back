package com.example.uade.tpo.demo.controller;

import com.example.uade.tpo.demo.entity.Pedido;
import com.example.uade.tpo.demo.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.getPedidoById(id);
        return pedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add-pedido")
    public ResponseEntity<Pedido> createPedido(@RequestBody Pedido pedido) {
        try {
            Pedido newPedido = pedidoService.newPedido(
                    pedido.getCart(),
                    pedido.getUserId(),
                    pedido.getDate(),
                    pedido.isDelivery(),
                    pedido.getAdress(),
                    pedido.getDeliveryDate(),
                    pedido.isEntregado(),
                    pedido.getSubtotal(),
                    pedido.getDescuento(),
                    pedido.getTotal()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(newPedido);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}