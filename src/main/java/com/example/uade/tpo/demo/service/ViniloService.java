package com.example.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.uade.tpo.demo.entity.Vinilo;
import com.example.uade.tpo.demo.exceptions.ViniloDuplicateException;
import com.example.uade.tpo.demo.exceptions.ViniloNotFoundException;
import com.example.uade.tpo.demo.model.ViniloUpdateDTO;

public interface ViniloService {
    Page<Vinilo> getVinilos(PageRequest pageRequest);

    Optional<Vinilo> getViniloById(Long id);

    List<Vinilo> getViniloByTitulo(String title);

    Vinilo newVinilo(String title, String subtitle, byte[] image, Double price, String genero, int stock) throws ViniloDuplicateException;
    
    Vinilo updateVinilo(Long id, ViniloUpdateDTO viniloUpdateDTO) throws ViniloNotFoundException;

    void deleteVinilo(Long id) throws ViniloNotFoundException;
    
    String getTitle(Long id);
    
    String getSubtitle(Long id);
    
    byte[] getImage(Long id);
    
    Double getPrecio(Long id);
    
    String getGenero(Long id);

}