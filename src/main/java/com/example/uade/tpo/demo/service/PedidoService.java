package com.example.uade.tpo.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.uade.tpo.demo.entity.Cuenta;
import com.example.uade.tpo.demo.entity.Pedido;

public interface PedidoService {
    Page<Pedido> getPedidos(PageRequest pageRequest);

    Optional<Pedido> getPedidoById(Long id);
    
    List<Pedido> getPedidosByUserId(Long id);

    Pedido newPedido(String cart, Cuenta cuenta, Date date, boolean delivery, String adress,
			Date deliveryDate, boolean entregado, double subtotal, double descuento, double total);
}