package com.example.uade.tpo.demo.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.uade.tpo.demo.exceptions.ViniloOutOfStockException;
import com.example.uade.tpo.demo.model.ViniloCarrito;
import com.example.uade.tpo.demo.model.ViniloDTO;
import com.example.uade.tpo.demo.service.ViniloService;
import com.example.uade.tpo.demo.service.ViniloServiceImpl;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ElementCollection
    private List<ViniloCarrito> cart;

	public Carrito() {
		this.cart = new ArrayList<>();
	}
	
	public double getSubtotal() {
		double total = 0;
		for (ViniloCarrito vinilo : cart) {
			total += (vinilo.getPrecio() * vinilo.getCantidad());
		}
		return total;
	}

	public void addViniloCarrito(ViniloCarrito vinilo) {
		cart.add(vinilo);
	}
	
	public void lessToCart(Long ViniloId, int cantidad) throws Exception {
		for (ViniloCarrito viniloDTO : cart) {
			if (viniloDTO.getViniloId() == ViniloId) {
				if (viniloDTO.getCantidad() - cantidad >= 1) {
					viniloDTO.setCantidad(viniloDTO.getCantidad() - cantidad);
				} else {
					viniloDTO.setCantidad(1);
				}
			}
		}
	}
	
	public void removeFromCart(Long ViniloId) throws Exception {
		for (ViniloCarrito viniloDTO : cart) {
			if (viniloDTO.getViniloId() == ViniloId) {
				cart.remove(viniloDTO);
			}
		}
	}

	public Long getId() {
		return id;
	}
	public List<ViniloCarrito> getCart() {
		return cart;
	}
	public List<ViniloDTO> getCartDTO() {
		List<ViniloDTO> vinilosDTO = new ArrayList<>();
		for (ViniloCarrito v : cart) {
			vinilosDTO.add(new ViniloDTO(
					v.getViniloId(),
					v.getTitle(),
					v.getSubtitle(),
					v.getImage(),
					v.getPrecio(),
					v.getGenero(),
					v.getCantidad()));
		}
		return vinilosDTO;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void setCart(List<ViniloCarrito> cart) {
		this.cart = cart;
	}
}