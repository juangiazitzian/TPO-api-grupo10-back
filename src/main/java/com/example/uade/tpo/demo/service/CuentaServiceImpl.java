package com.example.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.demo.entity.Carrito;
import com.example.uade.tpo.demo.entity.Cuenta;
import com.example.uade.tpo.demo.entity.Vinilo;
import com.example.uade.tpo.demo.exceptions.CuentaDuplicateException;
import com.example.uade.tpo.demo.exceptions.CuentaNotFoundException;
import com.example.uade.tpo.demo.exceptions.DescuentoUsedException;
import com.example.uade.tpo.demo.exceptions.ViniloOutOfStockException;
import com.example.uade.tpo.demo.model.ViniloCarrito;
import com.example.uade.tpo.demo.repository.CarritoRepository;
import com.example.uade.tpo.demo.repository.CuentaRepository;

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

    @Override
    public Cuenta newCuenta(String name, String lastName, String username, String password) throws CuentaDuplicateException {
        Optional<Cuenta> existingCuenta = cuentaRepository.findByUsername(username);
        if (existingCuenta.isPresent()) {
            throw new CuentaDuplicateException();
        } else {
        	Long carrito = carritoService.newCarrito();
            Cuenta newCuenta = new Cuenta(name, lastName, username, password, carrito);
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
        Vinilo vinilo = viniloService.getViniloById(viniloId).get();;
		boolean already = false;
		for (ViniloCarrito viniloDTO : carrito.getCart()) {
			if (viniloDTO.getViniloId() == viniloId) {
				already = true;
				if (vinilo.getStock() - (viniloDTO.getCantidad() + cantidad) >= 0) {
					viniloDTO.setCantidad(viniloDTO.getCantidad() + cantidad);
				} else {
					throw new ViniloOutOfStockException();
				}
			}
		}
		if (!already) {
			if (vinilo.getStock() - cantidad >= 0) {
				carrito.addViniloCarrito(new ViniloCarrito(viniloId, cantidad));
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
            descuentos.add(code);
            cuenta.setDescuentosUsados(descuentos);
            cuentaRepository.save(cuenta);
        } else {
            throw new CuentaNotFoundException();
        }
    }
}
