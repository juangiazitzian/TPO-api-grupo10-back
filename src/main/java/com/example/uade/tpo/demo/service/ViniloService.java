package com.example.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.uade.tpo.demo.entity.Vinilo;
import com.example.uade.tpo.demo.exceptions.ViniloDuplicateException;
import com.example.uade.tpo.demo.exceptions.ViniloNotFoundException;

public interface ViniloService {
    Page<Vinilo> getVinilos(PageRequest pageRequest);

    Optional<Vinilo> getViniloById(Long id);

    List<Vinilo> getViniloByTitulo(String title);

    Vinilo newVinilo(String title, String subtitle, String image, Double price, String genero, int stock) throws ViniloDuplicateException;
    
    Vinilo updateVinilo(Long id, String title, String subtitle, String image, Double price, String genero, int stock) throws ViniloNotFoundException;

    void deleteVinilo(Long id) throws ViniloNotFoundException;
    
    String getTitle(Long id);
    
    String getSubtitle(Long id);
    
    String getImage(Long id);
    
    Double getPrecio(Long id);
    
    String getGenero(Long id);
}