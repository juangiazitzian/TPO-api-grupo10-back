package com.example.uade.tpo.demo.controller;

import com.example.uade.tpo.demo.entity.Vinilo;
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
    public ResponseEntity<Page<Vinilo>> getVinilos(@RequestParam(required = false) Integer page,
    												@RequestParam(required = false) Integer size) {
        Page<Vinilo> vinilos = viniloService.getVinilos(PageRequest.of(page, size));
        return ResponseEntity.ok().body(vinilos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vinilo> getViniloById(@PathVariable Long id) {
        Optional<Vinilo> vinilo = viniloService.getViniloById(id);
        return vinilo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Vinilo>> getViniloByTitulo(@RequestParam String title) {
        List<Vinilo> vinilos = viniloService.getViniloByTitulo(title);
        return ResponseEntity.ok().body(vinilos);
    }

    @PostMapping
    public ResponseEntity<Vinilo> createVinilo(@RequestBody Vinilo vinilo) {
        try {
            Vinilo newVinilo = viniloService.newVinilo(
                    vinilo.getTitle(),
                    vinilo.getSubtitle(),
                    vinilo.getImage(),
                    vinilo.getPrice(),
                    vinilo.getGenero()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(newVinilo);
        } catch (ViniloDuplicateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
