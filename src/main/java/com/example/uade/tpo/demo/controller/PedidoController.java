package com.example.uade.tpo.demo.controller;

import com.example.uade.tpo.demo.entity.Cuenta;
import com.example.uade.tpo.demo.entity.Pedido;
import com.example.uade.tpo.demo.exceptions.PedidoNotFoundException;
import com.example.uade.tpo.demo.service.CuentaService;
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

    @Autowired
    private CuentaService cuentaService;

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
    public ResponseEntity<Pedido> createPedido(@RequestParam String username,
                                               @RequestParam boolean delivery,
                                               @RequestParam String adress,
                                               @RequestParam String descuento,
                                               @RequestParam String metodoPago ) {
        try {
            Optional<Cuenta> optionalCuenta = cuentaService.getCuentaByUsername(username);
            if (!optionalCuenta.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            Cuenta cuenta = optionalCuenta.get();
            Pedido newPedido = pedidoService.newPedido(cuenta, delivery, adress, descuento, metodoPago);
            return ResponseEntity.status(HttpStatus.CREATED).body(newPedido);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable Long id, @RequestParam boolean delivery,
    											@RequestParam String adress,
    											@RequestParam boolean entregado, @RequestParam String metodoPago) {
        try {
            Pedido updatedPedido = pedidoService.updatePedido(id, delivery, adress, entregado, metodoPago);
            return ResponseEntity.ok(updatedPedido);
        } catch (PedidoNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        try {
            pedidoService.deletePedido(id);
            return ResponseEntity.noContent().build();
        } catch (PedidoNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
