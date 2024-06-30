package com.example.uade.tpo.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.uade.tpo.demo.entity.Cuenta;
import com.example.uade.tpo.demo.entity.Descuento;
import com.example.uade.tpo.demo.entity.Factura;
import com.example.uade.tpo.demo.entity.Pedido;
import com.example.uade.tpo.demo.exceptions.DescuentoDuplicateException;
import com.example.uade.tpo.demo.exceptions.DescuentoNotFoundException;

public interface FacturaService {
    Page<Factura> getFacturas(PageRequest pageRequest);

    Optional<Factura> getFacturaById(Long id);

    Factura newFactura(Pedido pedido, Long numFactura) ;
    }