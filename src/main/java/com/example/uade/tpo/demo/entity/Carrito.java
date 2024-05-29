package com.example.uade.tpo.demo.entity;

import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data
@Entity
public class Carrito {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ElementCollection
	@CollectionTable(name = "cart_items", joinColumns = @JoinColumn(name = "carrito_id"))
	@MapKeyColumn(name = "item_key", nullable = false)
	@Column(name = "item_value", nullable = false)
	private Map<Integer, Integer> cart;
	
	
	public Carrito() {
	}
	
    public Carrito(Map<Integer, Integer> cart) {
		this.cart = cart;
	}
    
    
    public Long getId() {
		return id;
	}
	public Map<Integer, Integer> getCart() {
		return cart;
	}

	public void setCart(Map<Integer, Integer> cart) {
		this.cart = cart;
	}

}

