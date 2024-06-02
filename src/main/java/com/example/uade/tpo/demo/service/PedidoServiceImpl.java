package com.example.uade.tpo.demo.service;

import java.util.Date;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.demo.entity.Pedido;
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
    public Pedido newPedido(Map<Integer, Integer> cart, String userId, Date date, boolean delivery, String adress,
			Date deliveryDate, boolean entregado, double subtotal, double descuento, double total) {
    	Pedido newPedido = new Pedido(cart, userId, date, delivery, adress, deliveryDate, entregado, subtotal, descuento, total);
            return pedidoRepository.save(newPedido);
        }
}