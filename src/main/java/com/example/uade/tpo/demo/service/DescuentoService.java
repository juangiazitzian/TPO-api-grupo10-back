package com.example.uade.tpo.demo.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.uade.tpo.demo.entity.Descuento;
import com.example.uade.tpo.demo.exceptions.DescuentoDuplicateException;
import com.example.uade.tpo.demo.exceptions.DescuentoNotFoundException;

public interface DescuentoService {
    Page<Descuento> getDescuentos(PageRequest pageRequest);

    Optional<Descuento> getDescuentoById(Long id);

    Descuento getDescuentoByCode(String Code);

    Descuento newDescuento(String Code, double Off) throws DescuentoDuplicateException;
    
    Descuento updateDescuento(Long id, String code, double off) throws DescuentoNotFoundException;

    void deleteDescuento(Long id) throws DescuentoNotFoundException;
}