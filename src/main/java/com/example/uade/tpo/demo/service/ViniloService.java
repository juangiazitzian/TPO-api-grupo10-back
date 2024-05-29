package com.example.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.uade.tpo.demo.entity.Vinilo;
import com.example.uade.tpo.demo.exceptions.ViniloDuplicateException;

public interface ViniloService {
    Page<Vinilo> getVinilos(PageRequest pageRequest);

    Optional<Vinilo> getViniloById(Long id);

    List<Vinilo> getViniloByTitulo(String title);

    Vinilo newVinilo(String title, String subtitle, String image, Double price, String genero) throws ViniloDuplicateException;
}