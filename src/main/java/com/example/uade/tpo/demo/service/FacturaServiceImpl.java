package com.example.uade.tpo.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.demo.entity.Factura;
import com.example.uade.tpo.demo.entity.Pedido;
import com.example.uade.tpo.demo.repository.FacturaRepository;

import jakarta.transaction.Transactional;

@Service
public class FacturaServiceImpl implements FacturaService{

    @Autowired 
    private FacturaRepository facturaRepository;

    @Override
    public Page<Factura> getFacturas(PageRequest pageable) {
        return facturaRepository.findAll(pageable);
    }

    @Override
    public Optional<Factura> getFacturaById(Long id) {
        return facturaRepository.findById(id);
    }

    @Transactional(rollbackOn = Throwable.class)
    @Override
    public Factura newFactura(Pedido pedido) {
        Factura newFactura = new Factura(pedido);
        return facturaRepository.save(newFactura);
    }
}
