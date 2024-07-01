package com.example.uade.tpo.demo.controller;

import com.example.uade.tpo.demo.entity.Factura;
import com.example.uade.tpo.demo.entity.Pedido;
import com.example.uade.tpo.demo.service.FacturaService;
import com.example.uade.tpo.demo.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private PedidoService pedidoService;
    
    @GetMapping
    public ResponseEntity<Page<Factura>> getPedidos(@RequestParam(defaultValue = "0") Integer page,
                                                   @RequestParam(defaultValue = "10") Integer size) {
        Page<Factura> facturas = facturaService.getFacturas(PageRequest.of(page, size));
        return ResponseEntity.ok().body(facturas);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Factura> getFacturaById(@PathVariable Long id) {
        Optional<Factura> facturas = facturaService.getFacturaById(id);
        return facturas.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    @PostMapping("/add-factura")
    public ResponseEntity<Factura> createFactura(@RequestParam Long pedidoId
                                                ) {
        try {
            Optional<Pedido> pedido = pedidoService.getPedidoById(pedidoId);
            Factura newFactura = facturaService.newFactura(pedido.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(newFactura);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
