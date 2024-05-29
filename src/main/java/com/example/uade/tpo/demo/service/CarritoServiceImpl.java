package com.example.uade.tpo.demo.service;

import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.demo.entity.Carrito;
import com.example.uade.tpo.demo.repository.CarritoRepository;

@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Override
    public Page<Carrito> getCarritos(PageRequest pageable) {
        return carritoRepository.findAll(pageable);
    }

    @Override
    public Optional<Carrito> getCarritoById(Long id) {
        return carritoRepository.findById(id);
    }

    @Override
    public Carrito newCarrito(Map<Integer, Integer> cart) {
    	Carrito newCarrito = new Carrito(cart);
            return carritoRepository.save(newCarrito);
        }
}
