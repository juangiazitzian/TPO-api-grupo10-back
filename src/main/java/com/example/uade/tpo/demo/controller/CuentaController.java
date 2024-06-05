package com.example.uade.tpo.demo.controller;

import com.example.uade.tpo.demo.entity.Cuenta;
import com.example.uade.tpo.demo.entity.Pedido;
import com.example.uade.tpo.demo.exceptions.CuentaDuplicateException;
import com.example.uade.tpo.demo.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public ResponseEntity<Page<Cuenta>> getCuentas(@RequestParam(defaultValue = "0") Integer page,
    												@RequestParam(defaultValue = "10") Integer size) {
        Page<Cuenta> cuentas = cuentaService.getCuentas(PageRequest.of(page, size));
        return ResponseEntity.ok().body(cuentas);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Cuenta> getCuentaById(@PathVariable Long id) {
        Optional<Cuenta> cuenta = cuentaService.getCuentaById(id);
        return cuenta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/username/{username}")
    public ResponseEntity<Cuenta> getCuentabyUsername(@PathVariable String username) {
        Optional<Cuenta> cuenta = cuentaService.getCuentaByUsername(username);
        return cuenta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("add-cuenta")
    public ResponseEntity<Cuenta> createCuenta(@RequestParam String name, String lastName, String username, String password, int discount, List<Pedido> pedidos) {
        try {
            Cuenta newcuenta = cuentaService.newCuenta(name, lastName, username, password, discount, pedidos);
            return ResponseEntity.status(HttpStatus.CREATED).body(newcuenta);
        } catch (CuentaDuplicateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}