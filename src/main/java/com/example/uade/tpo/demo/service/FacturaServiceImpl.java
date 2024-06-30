package com.example.uade.tpo.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.demo.entity.Descuento;
import com.example.uade.tpo.demo.entity.Factura;
import com.example.uade.tpo.demo.entity.Pedido;
import com.example.uade.tpo.demo.exceptions.DescuentoDuplicateException;
import com.example.uade.tpo.demo.repository.FacturaRepository;

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

    @Override
    public Factura newFactura(Pedido pedido, Long numFactura) {
        Factura newFactura = new Factura(pedido,numFactura);
        return facturaRepository.save(newFactura);
        
    }


}
