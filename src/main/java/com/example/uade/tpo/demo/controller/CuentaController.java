package com.example.uade.tpo.demo.controller;

import com.example.uade.tpo.demo.entity.Cuenta;
import com.example.uade.tpo.demo.exceptions.CuentaDuplicateException;
import com.example.uade.tpo.demo.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> getCuentaById(@PathVariable Long id) {
        Optional<Cuenta> cuenta = cuentaService.getCuentaById(id);
        return cuenta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("add-cuenta")
    public ResponseEntity<Cuenta> createCuenta(@RequestParam String name, String lastName, String username, String password, int discount) {
        try {
            Cuenta newcuenta = cuentaService.newCuenta(name, lastName, username, password, discount);
            return ResponseEntity.status(HttpStatus.CREATED).body(newcuenta);
        } catch (CuentaDuplicateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}