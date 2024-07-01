package com.example.uade.tpo.demo.controller;

import com.example.uade.tpo.demo.entity.Cuenta;
import com.example.uade.tpo.demo.entity.Role;
import com.example.uade.tpo.demo.exceptions.CuentaDuplicateException;
import com.example.uade.tpo.demo.exceptions.CuentaNotFoundException;
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
    public ResponseEntity<Cuenta> createCuenta(@RequestParam String name,
												@RequestParam String lastName,
												@RequestParam String username,
												@RequestParam String password,
                                                @RequestParam Role role) {
        try {
            Cuenta newcuenta = cuentaService.newCuenta(name, lastName, username, password,role);
            return ResponseEntity.status(HttpStatus.CREATED).body(newcuenta);
        } catch (CuentaDuplicateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Cuenta> updateCuenta(@PathVariable Long id,
                                                @RequestParam String name,
                                                @RequestParam String lastName,
                                                @RequestParam String username,
                                                @RequestParam String password,
                                                @RequestParam int discount) {
        try {
            Cuenta updatedCuenta = cuentaService.updateCuenta(id, name, lastName, username, password);
            return ResponseEntity.ok(updatedCuenta);
        } catch (CuentaNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable Long id) {
        try {
            cuentaService.deleteCuenta(id);
            return ResponseEntity.noContent().build();
        } catch (CuentaNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/add-descuentoUsado/{id}")
    public ResponseEntity<Cuenta> addDescuentoUsado(@PathVariable Long id, @RequestParam String code) throws Exception {
        try {
            cuentaService.addDescuentoUsado(id, code);
            return ResponseEntity.ok().build();
        } catch (CuentaNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/add-item-cart")
    public void addItemCart(@RequestParam String username, @RequestParam Long viniloId, @RequestParam int cantidad) {
    	try {
			cuentaService.addItem(username, viniloId, cantidad);
		} catch (Exception e) {
		}
    }
    
    @PutMapping("/less-item-cart")
    public void lessItemCart(@RequestParam String username, @RequestParam Long viniloId, @RequestParam int cantidad) {
    	try {
			cuentaService.lessItem(username, viniloId, cantidad);
		} catch (Exception e) {
		}
    }
    
}