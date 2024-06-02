package com.example.uade.tpo.demo.service;

import java.util.Date;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.uade.tpo.demo.entity.Pedido;

public interface PedidoService {
    Page<Pedido> getPedidos(PageRequest pageRequest);

    Optional<Pedido> getPedidoById(Long id);

    Pedido newPedido(Map<Integer, Integer> cart, String userId, Date date, boolean delivery, String adress,
			Date deliveryDate, boolean entregado, double subtotal, double descuento, double total);
}