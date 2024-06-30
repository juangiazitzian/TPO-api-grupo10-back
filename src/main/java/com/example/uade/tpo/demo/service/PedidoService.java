package com.example.uade.tpo.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.uade.tpo.demo.entity.Cuenta;
import com.example.uade.tpo.demo.entity.Pedido;
import com.example.uade.tpo.demo.entity.Vinilo;
import com.example.uade.tpo.demo.model.ViniloDTO;
import com.example.uade.tpo.demo.exceptions.PedidoNotFoundException;

public interface PedidoService {
    Page<Pedido> getPedidos(PageRequest pageRequest);

    Optional<Pedido> getPedidoById(Long id);
    
    List<Pedido> getPedidosByUserId(Long id);

    Pedido newPedido( List<ViniloDTO> cart, Cuenta cuenta, boolean delivery, String adress, boolean entregado, double subtotal, double descuento, double total, String metodoPago);

    Pedido updatePedido(Long id, List<ViniloDTO> cart, Cuenta cuenta, Date date, boolean delivery, String adress,
            Date deliveryDate, boolean entregado, double subtotal, double descuento, double total, String metodoPago) throws PedidoNotFoundException;

    void deletePedido(Long id) throws PedidoNotFoundException;

    public List<ViniloDTO> getCarrito(Long pedidoId) throws PedidoNotFoundException;

    void addCarrito(Long id, Integer cantidad, Long viniloId) throws Exception;

}