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
import com.example.uade.tpo.demo.model.ViniloDTO;
import com.example.uade.tpo.demo.exceptions.CuentaNotFoundException;
import com.example.uade.tpo.demo.exceptions.DescuentoUsedException;
import com.example.uade.tpo.demo.exceptions.PedidoNotFoundException;
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

        public Pedido newPedido(List<ViniloDTO> cart, Cuenta cuenta, boolean delivery, String adress, boolean entregado, double subtotal, double descuento, double total, String metodoPago) {
        Pedido pedido = new Pedido(cart, cuenta, delivery, adress, entregado, subtotal, descuento, total, metodoPago);
        return pedidoRepository.save(pedido);
    }

    public Pedido updatePedido(Long id, List<ViniloDTO> cart, Cuenta cuenta, Date date, boolean delivery, String adress,
    Date deliveryDate, boolean entregado, double subtotal, double descuento, double total, String metodoPago) throws PedidoNotFoundException {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(id);
        if (optionalPedido.isPresent()) {
            Pedido pedido = optionalPedido.get();
            pedido.setCart(cart);
            pedido.setCuenta(cuenta);
            pedido.setDate(date);
            pedido.setDeliveryDate(deliveryDate);
            pedido.setDelivery(delivery);
            pedido.setAdress(adress);
            pedido.setEntregado(entregado);
            pedido.setSubtotal(subtotal);
            pedido.setDescuento(descuento);
            pedido.setTotal(total);
            pedido.setMetodoPago(metodoPago);
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

    
    @Override
    public void addCarrito(Long pedidoId, Integer cantidad, Long viniloId) throws Exception {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(pedidoId);
        if (optionalPedido.isPresent()) {
            Pedido pedido = optionalPedido.get();
            List<ViniloDTO> cart = pedido.getCart();

            Optional<Vinilo> optionalVinilo = viniloRepository.findById(viniloId);
            if (!optionalVinilo.isPresent()){
                throw new Exception("Vinilo no existe");
            }

            Vinilo vinilo = optionalVinilo.get();
            if ( vinilo.getStock() - cantidad < 0 ){
                throw new Exception("Limite de stock alcanzado");
            }
            else{
                ViniloDTO newViniloDTO = new ViniloDTO( vinilo.getId(), vinilo.getTitle(), vinilo.getSubtitle(), vinilo.getImage(), vinilo.getPrice(), vinilo.getGenero(), cantidad);
                cart.add(newViniloDTO);
                pedido.setCart(cart);
                pedidoRepository.save(pedido);
            }
            
        } else {
            throw new PedidoNotFoundException();
        }
    }

    @Override
    public List<ViniloDTO> getCarrito(Long pedidoId) throws PedidoNotFoundException {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(pedidoId);
        if (optionalPedido.isPresent()) {
            Pedido pedido = optionalPedido.get();
            return pedido.getCart();
        } else {
            throw new PedidoNotFoundException();
        }
}

}
