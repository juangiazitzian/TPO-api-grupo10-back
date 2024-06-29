package com.example.uade.tpo.demo.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
@Table
public class Pedido {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<String> cart;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Cuenta cuenta;
	
	@Column(nullable = false)
	private String date;
	
	@Column(nullable = false)
	private boolean delivery;
	
	@Column
	private String adress;
	
	@Column(nullable = false)
	private String deliveryDate;

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
	
    public Pedido( List<String> cart, Cuenta cuenta, String date, boolean delivery, String adress,
    String deliveryDate, boolean entregado, double subtotal, double descuento, double total) {
        this.cuenta = cuenta;
        this.cart = cart;
        this.date = date;
        this.delivery = delivery;
        this.adress = adress;
        this.deliveryDate = deliveryDate;
        this.entregado = entregado;
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.total = total;
	}

	

}

