package com.example.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.demo.entity.Carrito;
import com.example.uade.tpo.demo.entity.Cuenta;
import com.example.uade.tpo.demo.entity.Descuento;
import com.example.uade.tpo.demo.entity.Role;
import com.example.uade.tpo.demo.entity.Vinilo;
import com.example.uade.tpo.demo.exceptions.CuentaDuplicateException;
import com.example.uade.tpo.demo.exceptions.CuentaNotFoundException;
import com.example.uade.tpo.demo.exceptions.DescuentoUsedException;
import com.example.uade.tpo.demo.exceptions.ViniloOutOfStockException;
import com.example.uade.tpo.demo.model.ViniloCarrito;
import com.example.uade.tpo.demo.repository.CarritoRepository;
import com.example.uade.tpo.demo.repository.CuentaRepository;

import jakarta.transaction.Transactional;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;
    
    @Autowired
    private CarritoRepository carritoRepository;
    
    @Autowired
    private CarritoService carritoService;
    
    @Autowired
    private ViniloService viniloService;
    
    @Autowired
    private DescuentoService descuentoService;

    @Override
    public Page<Cuenta> getCuentas(PageRequest pageable) {
        return cuentaRepository.findAll(pageable);
    }

    @Override
    public Optional<Cuenta> getCuentaById(Long id) {
        return cuentaRepository.findById(id);
    }

    @Override
    public Optional<Cuenta> getCuentaByUsername(String username) {
        return cuentaRepository.findByUsername(username);
    }

    @Transactional(rollbackOn = Throwable.class)
    @Override
    public Cuenta newCuenta(String name, String lastName, String username, String password, Role role) throws CuentaDuplicateException {
        Optional<Cuenta> existingCuenta = cuentaRepository.findByUsername(username);
        if (existingCuenta.isPresent()) {
            throw new CuentaDuplicateException();
        } else {
        	Long carrito = carritoService.newCarrito();
            Cuenta newCuenta = new Cuenta(name, lastName, username, password, carrito, role);
            return cuentaRepository.save(newCuenta);
        }
    }

    @Override
    public Cuenta updateCuenta(Long id, String name, String lastName, String username, String password) throws CuentaNotFoundException {
        Optional<Cuenta> optionalCuenta = cuentaRepository.findById(id);
        if (optionalCuenta.isPresent()) {
            Cuenta cuenta = optionalCuenta.get();
            cuenta.setName(name);
            cuenta.setLastName(lastName);
            cuenta.setUsername(username);
            cuenta.setPassword(password);
            return cuentaRepository.save(cuenta);
        } else {
            throw new CuentaNotFoundException();
        }
    }
    
    @Override
    public void deleteCuenta(Long id) throws CuentaNotFoundException {
        if (cuentaRepository.existsById(id)) {
            cuentaRepository.deleteById(id);
        } else {
            throw new CuentaNotFoundException();
        }
    }
    
    @Override
    public void addItem(String username, Long viniloId, int cantidad) throws Exception {
    	Cuenta cuenta = cuentaRepository.findByUsername(username).get();
        Carrito carrito = carritoRepository.findById(cuenta.getCartId()).get();
        Vinilo vinilo = viniloService.getViniloById(viniloId).get();
		boolean already = false;
		for (ViniloCarrito viniloCarrito : carrito.getCart()) {
	    	System.out.println(6);
			if (viniloCarrito.getViniloId() == viniloId) {
		    	System.out.println(7);
				already = true;
		    	System.out.println(8);
				if (vinilo.getStock() - (viniloCarrito.getCantidad() + cantidad) >= 0) {
					viniloCarrito.setCantidad(viniloCarrito.getCantidad() + cantidad);
			    	System.out.println(9);
				} else {
			    	System.out.println(10);
					throw new ViniloOutOfStockException();
				}
			}
		}
		if (!already) {
			if (vinilo.getStock() - cantidad >= 0) {
				ViniloCarrito newVinilo = new ViniloCarrito(viniloId, cantidad);
				carrito.addViniloCarrito(newVinilo);
			} else {
				throw new ViniloOutOfStockException();
			}
		}
        carritoRepository.save(carrito);
    }
    
    @Override
    public void lessItem(String username, Long viniloId, int cantidad) throws Exception {
    	Cuenta cuenta = cuentaRepository.findByUsername(username).get();
        Carrito carrito = carritoRepository.findById(cuenta.getCartId()).get();
        carrito.lessToCart(viniloId, cantidad);
        carritoRepository.save(carrito);
    }

    @Override
    public void addDescuentoUsado(Long cuentaId, String code) throws DescuentoUsedException, CuentaNotFoundException, Exception {
        Optional<Cuenta> optionalCuenta = cuentaRepository.findById(cuentaId);
        if (optionalCuenta.isPresent()) {
            Cuenta cuenta = optionalCuenta.get();
            List<String> descuentos = cuenta.getDescuentosUsados();
            if (descuentos.contains(code)) {
                throw new DescuentoUsedException();
            }
            for (Descuento descuento : descuentoService.getDescuentosList()) {
            	if (code.equals(descuento.getCode())) {
	            	descuentos.add(code);
		            cuenta.setDescuentosUsados(descuentos);
		            cuentaRepository.save(cuenta);
            	}
            }
        } else {
            throw new CuentaNotFoundException();
        }
    }
}
