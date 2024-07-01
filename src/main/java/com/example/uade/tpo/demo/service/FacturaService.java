package com.example.uade.tpo.demo.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.uade.tpo.demo.entity.Factura;
import com.example.uade.tpo.demo.entity.Pedido;

public interface FacturaService {
    Page<Factura> getFacturas(PageRequest pageRequest);

    Optional<Factura> getFacturaById(Long id);

    Factura newFactura(Pedido pedido) ;
}