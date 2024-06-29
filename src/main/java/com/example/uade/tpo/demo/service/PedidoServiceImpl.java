package com.example.uade.tpo.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.demo.entity.Cuenta;
import com.example.uade.tpo.demo.entity.Pedido;
import com.example.uade.tpo.demo.entity.Vinilo;
import com.example.uade.tpo.demo.entity.ViniloDTO;
import com.example.uade.tpo.demo.exceptions.PedidoNotFoundException;
import com.example.uade.tpo.demo.repository.PedidoRepository;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

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

        public Pedido newPedido(List<ViniloDTO> cart, Cuenta cuenta, String date, boolean delivery, String adress,
                            String deliveryDate, boolean entregado, double subtotal, double descuento, double total) {
        Pedido pedido = new Pedido(cart, cuenta, date, delivery, adress, deliveryDate, entregado, subtotal, descuento, total);
        return pedidoRepository.save(pedido);
    }

    public Pedido updatePedido(Long id, List<ViniloDTO> cart, Cuenta cuenta, String date, boolean delivery, String adress,
                               String deliveryDate, boolean entregado, double subtotal, double descuento, double total) throws PedidoNotFoundException {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(id);
        if (optionalPedido.isPresent()) {
            Pedido pedido = optionalPedido.get();
            pedido.setCart(cart);
            pedido.setCuenta(cuenta);
            pedido.setDate(date);
            pedido.setDelivery(delivery);
            pedido.setAdress(adress);
            pedido.setDeliveryDate(deliveryDate);
            pedido.setEntregado(entregado);
            pedido.setSubtotal(subtotal);
            pedido.setDescuento(descuento);
            pedido.setTotal(total);
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
