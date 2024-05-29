package com.example.uade.tpo.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.uade.tpo.demo.repository.entity.Vinilo;
import com.example.uade.tpo.demo.repository.ViniloRepository;
import com.example.uade.tpo.demo.exceptiones.ViniloDuplicateException;
import com.example.uade.tpo.demo.service.ViniloService;
import com.example.uade.tpo.demo.service.viniloServiceImpl;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("vinyls")
public class ViniloController  {

    @Autowired
    private ViniloService vinylService;

    @GetMapping
    public ResponseEntity<Page<Vinilo>> getVinyls(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(vinylService.getVinyls(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(vinylService.getVinyls(PageRequest.of(page, size)));
    }

    @GetMapping("/{vinylId}")
    public ResponseEntity<Vinilo> getVinylById(@PathVariable Long viniloId) {
        Optional<Vinilo> result = ViniloService (viniloId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> createVinyl(@RequestBody viniloRe vinylRequest)
            throws ViniloDuplicateException {
        Vinilo result = vinylService.createVinyl(
            vinylRequest.getTitle(),
            vinylRequest.getSubtitle(),
            vinylRequest.getImageSrc(),
            vinylRequest.getPrice(),
            vinylRequest.getGenero()
        );
        return ResponseEntity.created(URI.create("/vinyls/" + result.getId())).body(result);
    }
}
