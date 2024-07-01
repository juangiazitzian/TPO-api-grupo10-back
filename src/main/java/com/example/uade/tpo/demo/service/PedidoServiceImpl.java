package com.example.uade.tpo.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.demo.entity.Cuenta;
import com.example.uade.tpo.demo.entity.Pedido;
import com.example.uade.tpo.demo.entity.Vinilo;
import com.example.uade.tpo.demo.exceptions.PedidoNotFoundException;
import com.example.uade.tpo.demo.model.ViniloCarrito;
import com.example.uade.tpo.demo.repository.PedidoRepository;
import com.example.uade.tpo.demo.repository.ViniloRepository;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private ViniloRepository viniloRepository;

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
    
    @Override
    public Pedido newPedido(Cuenta cuenta, boolean delivery, String adress, String descuento, String metodoPago) {
        Pedido pedido = new Pedido(cuenta, delivery, adress, descuento, metodoPago);
        CarritoService carritoService = new CarritoServiceImpl();
        for (ViniloCarrito viniloCarrito : carritoService.getCarritoById(cuenta.getCartId()).get().getCart()) {
        	Optional<Vinilo> viniloOpt = viniloRepository.findById(viniloCarrito.getViniloId());
        	if (viniloOpt.isPresent()) {
        		viniloOpt.get().setStock(viniloOpt.get().getStock() - viniloCarrito.getCantidad());
        		viniloRepository.save(viniloOpt.get());
        	}
        }
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido updatePedido(Long id, boolean delivery, String adress, Date deliveryDate, boolean entregado, String metodoPago)
    		throws PedidoNotFoundException {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(id);
        if (optionalPedido.isPresent()) {
            Pedido pedido = optionalPedido.get();
            pedido.setDeliveryDate(deliveryDate);
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
