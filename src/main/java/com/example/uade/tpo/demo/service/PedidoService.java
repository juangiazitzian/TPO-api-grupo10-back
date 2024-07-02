package com.example.uade.tpo.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.uade.tpo.demo.entity.Cuenta;
import com.example.uade.tpo.demo.entity.Pedido;
import com.example.uade.tpo.demo.exceptions.PedidoNotFoundException;

public interface PedidoService {
    Page<Pedido> getPedidos(PageRequest pageRequest);

    Optional<Pedido> getPedidoById(Long id);
    
    List<Pedido> getPedidosByUserId(Long id);

    Pedido newPedido(Cuenta cuenta, boolean delivery, String adress, String descuento, String metodoPago);

    Pedido updatePedido(Long id, boolean delivery, String adress, boolean entregado, String metodoPago)
    		throws PedidoNotFoundException;
    
    Pedido deliveredPedido(Long id) throws PedidoNotFoundException;

    void deletePedido(Long id) throws PedidoNotFoundException;

}