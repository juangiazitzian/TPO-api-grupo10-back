package com.example.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.uade.tpo.demo.entity.Descuento;
import com.example.uade.tpo.demo.exceptions.DescuentoDuplicateException;
import com.example.uade.tpo.demo.exceptions.DescuentoNotFoundException;

public interface DescuentoService {
    Page<Descuento> getDescuentos(PageRequest pageRequest);

    Optional<Descuento> getDescuentoById(Long id);

    List<Descuento> getDescuentoByCode(String Code);

    Descuento newDescuento(String Code, Double Off) throws DescuentoDuplicateException;
    
    Descuento updateDescuento(Long id, String code,Double off) throws DescuentoNotFoundException;

    void deleteDescuento(Long id) throws DescuentoNotFoundException;
}