package com.example.uade.tpo.demo.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.demo.entity.Carrito;
import com.example.uade.tpo.demo.repository.CarritoRepository;
import com.example.uade.tpo.demo.repository.CuentaRepository;

@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;
    
    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public Optional<Carrito> getCarritoById(Long id) {
        return carritoRepository.findById(id);
    }
    
    @SuppressWarnings("deprecation")
	@Override
    public Optional<Carrito> getCarritoByCuentaId(Long id) {
        return carritoRepository.findById(cuentaRepository.getById(id).getCartId());
    }
    
    @Override
    public Long newCarrito() {
    	Carrito newCarrito = new Carrito();
        Carrito carrito = carritoRepository.save(newCarrito);
        return carrito.getId();
    }

	@Override
	public double totalCarrito(Long id) {
		return getCarritoById(id).get().getSubtotal();
	}
}
