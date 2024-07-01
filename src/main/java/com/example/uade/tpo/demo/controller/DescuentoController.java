package com.example.uade.tpo.demo.controller;

import com.example.uade.tpo.demo.entity.Descuento;
import com.example.uade.tpo.demo.exceptions.DescuentoNotFoundException;
import com.example.uade.tpo.demo.exceptions.DescuentoDuplicateException;
import com.example.uade.tpo.demo.service.DescuentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/descuentos")
public class DescuentoController {

    @Autowired
    private DescuentoService descuentoService;

    @GetMapping
    public ResponseEntity<Page<Descuento>> getDescuentos(@RequestParam(defaultValue = "0") Integer page,
                                                   @RequestParam(defaultValue = "10") Integer size) {
        Page<Descuento> descuentos =  descuentoService.getDescuentos(PageRequest.of(page, size));
        return ResponseEntity.ok().body(descuentos);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Descuento> getDescuentoById(@PathVariable Long id) {
        Optional<Descuento> Descuento = descuentoService.getDescuentoById(id);
        return Descuento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/titulo/{code}")
    public ResponseEntity<Descuento> getDescuentoByTitulo(@PathVariable String code) {
        Descuento descuento = descuentoService.getDescuentoByCode(code);
        return ResponseEntity.ok().body(descuento);
    }

    @PostMapping("/add-Descuento")
    public ResponseEntity<Descuento> createDescuento(@RequestParam String code, Double off) {
        try {
            Descuento newDescuento = descuentoService.newDescuento(
            		code, off
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(newDescuento);
        } catch (DescuentoDuplicateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/update/{id}")
        public ResponseEntity<Descuento> updateDescuento(@PathVariable Long id,
                                                @RequestParam String code,
                                                @RequestParam Double off) {
        try {
            Descuento updatedDescuento = descuentoService.updateDescuento(id, code, off);
            return ResponseEntity.ok(updatedDescuento);
        } catch (DescuentoNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDescuento(@PathVariable Long id) {
        try {
            descuentoService.deleteDescuento(id);
            return ResponseEntity.noContent().build();
        } catch (DescuentoNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
