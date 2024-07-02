package com.example.uade.tpo.demo.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.demo.entity.Carrito;
import com.example.uade.tpo.demo.model.ViniloCarrito;
import com.example.uade.tpo.demo.repository.CarritoRepository;
import com.example.uade.tpo.demo.repository.CuentaRepository;

import jakarta.transaction.Transactional;

@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;
    
    @Autowired
    private CuentaRepository cuentaRepository;
    
    @Autowired
    private ViniloService viniloService;

    @Override
    public Optional<Carrito> getCarritoById(Long id) {
        return carritoRepository.findById(id);
    }
    
    @SuppressWarnings("deprecation")
	@Override
    public Optional<Carrito> getCarritoByCuentaId(Long id) {
        return carritoRepository.findById(cuentaRepository.getById(id).getCartId());
    }
    
    @Transactional(rollbackOn = Throwable.class)
    @Override
    public Long newCarrito() {
    	Carrito newCarrito = new Carrito();
    	if (carritoRepository == null) System.out.println("null");
        Carrito carrito = carritoRepository.save(newCarrito);
        return carrito.getId();
    }

	@Override
	public double totalCarrito(Long id) {
		return getSubtotal(id);
	}
	
	public double getSubtotal(Long id) {
		double total = 0;
		for (ViniloCarrito vinilo : getCarritoById(id).get().getCart()) {
			total += (viniloService.getViniloById(vinilo.getViniloId()).get().getPrice() * vinilo.getCantidad());
		}
		return total;
	}
}
