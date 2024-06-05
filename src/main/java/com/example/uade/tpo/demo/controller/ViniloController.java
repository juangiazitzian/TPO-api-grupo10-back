package com.example.uade.tpo.demo.controller;

import com.example.uade.tpo.demo.entity.Vinilo;
import com.example.uade.tpo.demo.exceptions.ViniloNotFoundException;
import com.example.uade.tpo.demo.exceptions.ViniloDuplicateException;
import com.example.uade.tpo.demo.service.ViniloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vinilos")
public class ViniloController {

    @Autowired
    private ViniloService viniloService;

    @GetMapping
    public ResponseEntity<Page<Vinilo>> getVinilos(@RequestParam(defaultValue = "0") Integer page,
                                                   @RequestParam(defaultValue = "10") Integer size) {
        Page<Vinilo> vinilos = viniloService.getVinilos(PageRequest.of(page, size));
        return ResponseEntity.ok().body(vinilos);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Vinilo> getViniloById(@PathVariable Long id) {
        Optional<Vinilo> vinilo = viniloService.getViniloById(id);
        return vinilo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/titulo/{title}")
    public ResponseEntity<List<Vinilo>> getViniloByTitulo(@PathVariable String title) {
        List<Vinilo> vinilos = viniloService.getViniloByTitulo(title);
        return ResponseEntity.ok().body(vinilos);
    }

    @PostMapping("/add-vinilo")
    public ResponseEntity<Vinilo> createVinilo(@RequestParam String title, String subtitle, String image, Double price, String genero) {
        try {
            Vinilo newVinilo = viniloService.newVinilo(
            		title, subtitle, image, price, genero
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(newVinilo);
        } catch (ViniloDuplicateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/update/{id}")
        public ResponseEntity<Vinilo> updateVinilo(@PathVariable Long id,
                                                @RequestParam String title,
                                                @RequestParam String subtitle,
                                                @RequestParam String image,
                                                @RequestParam Double price,
                                                @RequestParam String genero) {
        try {
            Vinilo updatedVinilo = viniloService.updateVinilo(id, title, subtitle, image, price, genero);
            return ResponseEntity.ok(updatedVinilo);
        } catch (ViniloNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVinilo(@PathVariable Long id) {
        try {
            viniloService.deleteVinilo(id);
            return ResponseEntity.noContent().build();
        } catch (ViniloNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
