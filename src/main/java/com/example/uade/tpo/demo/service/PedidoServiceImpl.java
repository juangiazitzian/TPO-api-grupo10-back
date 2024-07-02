package com.example.uade.tpo.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.demo.entity.Carrito;
import com.example.uade.tpo.demo.entity.Cuenta;
import com.example.uade.tpo.demo.entity.Pedido;
import com.example.uade.tpo.demo.entity.Vinilo;
import com.example.uade.tpo.demo.exceptions.CuentaNotFoundException;
import com.example.uade.tpo.demo.exceptions.DescuentoUsedException;
import com.example.uade.tpo.demo.exceptions.PedidoNotFoundException;
import com.example.uade.tpo.demo.model.ViniloCarrito;
import com.example.uade.tpo.demo.model.ViniloDTO;
import com.example.uade.tpo.demo.repository.CarritoRepository;
import com.example.uade.tpo.demo.repository.PedidoRepository;
import com.example.uade.tpo.demo.repository.ViniloRepository;

import jakarta.transaction.Transactional;


@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private ViniloRepository viniloRepository;

    @Autowired
    private CarritoRepository carritoRepository;
    
    @Autowired
    private ViniloService viniloService;

    @Autowired
    private CarritoService carritoService;
    
    @Autowired
    private DescuentoService descuentoService;

    @Autowired
    private CuentaService cuentaService;

    @Override
    public Page<Pedido> getPedidos(PageRequest pageable) {
        return pedidoRepository.findAll(pageable);
    }

    @Override
    public Optional<Pedido> getPedidoById(Long id) {
        return pedidoRepository.findById(id);
    }
    
    @Override
    public List<Pedido> getPedidosByUserId(Long id) {
        return pedidoRepository.findByUserId(id);
    }
    
    @Transactional(rollbackOn = Throwable.class)
    @Override
    public Pedido newPedido(Cuenta cuenta, boolean delivery, String adress, String descuento, String metodoPago) {
		List<ViniloDTO> vinilosDTO = new ArrayList<>();
		for (ViniloCarrito v : carritoService.getCarritoById(cuenta.getCartId()).get().getCart()) {
			Long id = v.getViniloId();
			vinilosDTO.add(new ViniloDTO(
					id,
					viniloService.getTitle(id),
					viniloService.getSubtitle(id),
					viniloService.getImage(id),
					viniloService.getPrecio(id),
					viniloService.getGenero(id),
					v.getCantidad()));
		}
        double descuentoOff = 0;
        if (descuentoService.getDescuentoByCode(descuento) != null){
            descuentoOff = descuentoService.getDescuentoByCode(descuento).getOff(); 
        }
        Pedido pedido = new Pedido(cuenta, vinilosDTO, delivery, adress, descuento, descuentoOff, metodoPago);
        try {
            cuentaService.addDescuentoUsado(cuenta.getId(), descuento);
        } catch (Exception e) {
        }
        for (ViniloCarrito viniloCarrito : carritoService.getCarritoById(cuenta.getCartId()).get().getCart()) {
        	Optional<Vinilo> viniloOpt = viniloRepository.findById(viniloCarrito.getViniloId());
        	if (viniloOpt.isPresent()) {
        		viniloOpt.get().setStock(viniloOpt.get().getStock() - viniloCarrito.getCantidad());
        		viniloRepository.save(viniloOpt.get());
        	}
        }
         System.out.println("Pedido a guardar: " + pedido);
        try{

             Pedido savedPedido = pedidoRepository.save(pedido);
            
            // Vacía el carrito del usuario
            Carrito carrito = carritoService.getCarritoById(cuenta.getCartId()).get();
            carrito.getCart().clear();  // Limpia el carrito
            carritoService.getCarritoById(cuenta.getCartId()).get().setCart(new ArrayList<>());  // Reasigna una lista vacía al carrito
            carritoRepository.save(carrito);  // Guarda los cambios en el carrito
            
            return savedPedido;
        } catch (Exception e){
            System.out.println("no se guardo el pedido");
            return pedido;
        }
    }

    @Override
    public Pedido updatePedido(Long id, boolean delivery, String adress, boolean entregado, String metodoPago)
    		throws PedidoNotFoundException {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(id);
        if (optionalPedido.isPresent()) {
            Pedido pedido = optionalPedido.get();
            pedido.setDelivery(delivery);
            pedido.setAdress(adress);
            pedido.setEntregado(entregado);
            pedido.setMetodoPago(metodoPago);
            return pedidoRepository.save(pedido);
        } else {
            throw new PedidoNotFoundException();
        }
    }
    
    @Override
    public Pedido deliveredPedido(Long id) throws PedidoNotFoundException {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(id);
        if (optionalPedido.isPresent()) {
            Pedido pedido = optionalPedido.get();
            pedido.setEntregado(!pedido.isEntregado());
            return pedidoRepository.save(pedido);
        } else {
            throw new PedidoNotFoundException();
        }
    }

    @Override
    public void deletePedido(Long id) throws PedidoNotFoundException {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
        } else {
            throw new PedidoNotFoundException();
        }
    }
}
