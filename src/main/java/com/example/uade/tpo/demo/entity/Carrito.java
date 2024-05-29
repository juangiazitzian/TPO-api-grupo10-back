package com.example.uade.tpo.demo.entity;

import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Carrito {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(nullable = false)
    private Map<Integer, Integer> cart;
	
	
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

