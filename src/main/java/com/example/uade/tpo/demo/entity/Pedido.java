package com.example.uade.tpo.demo.entity;

import java.util.Date;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data
@Entity
@Table
public class Pedido {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ElementCollection
	@CollectionTable(name = "cart_items", joinColumns = @JoinColumn(name = "carrito_id"))
	@MapKeyColumn(name = "item_key", nullable = false)
	@Column(name = "item_value", nullable = false)
	private Map<Integer, Integer> cart;
	
	@Column(nullable = false)
	private String userId;

	@Column(nullable = false)
	private Date date;
	
	@Column(nullable = false)
	private boolean delivery;
	
	private String adress;
	
	@Column(nullable = false)
	private Date deliveryDate;

	@Column(nullable = false)
	private boolean entregado;
	
	@Column(nullable = false)
	private double subtotal;
	
	@Column(nullable = false)
	private double descuento;
	
	@Column(nullable = false)
	private double total;
	
	
	public Pedido() {
	}
	
	public Pedido(Map<Integer, Integer> cart, String userId, Date date, boolean delivery, String adress,
			Date deliveryDate, boolean entregado, double subtotal, double descuento, double total) {
		this.cart = cart;
		this.userId = userId;
		this.date = date;
		this.delivery = delivery;
		this.adress = adress;
		this.deliveryDate = deliveryDate;
		this.entregado = entregado;
		this.subtotal = subtotal;
		this.descuento = descuento;
		this.total = total;
	}

	public Long getId() {
		return id;
	}
	public Map<Integer, Integer> getCart() {
		return cart;
	}
	public String getUserId() {
		return userId;
	}
	public Date getDate() {
		return date;
	}
	public boolean isDelivery() {
		return delivery;
	}
	public String getAdress() {
		return adress;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public boolean isEntregado() {
		return entregado;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public double getDescuento() {
		return descuento;
	}
	public double getTotal() {
		return total;
	}

	public void setCart(Map<Integer, Integer> cart) {
		this.cart = cart;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setDelivery(boolean delivery) {
		this.delivery = delivery;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}
	public void setTotal(double total) {
		this.total = total;
	}

}

