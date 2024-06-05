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
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
@Table
public class Pedido {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(nullable = false)
	private String cart;
	
	@Column(nullable = false)
	private String userId;

	@Column(nullable = false)
	private Date date;
	
	@Column(nullable = false)
	private boolean delivery;
	
	@Column
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

    @ManyToOne
    @JoinColumn(name = "userId") // Nombre de la columna que representa la clave externa
    private Cuenta cuenta;
	
	
	public Pedido() {
	}
	
    public Pedido(Cuenta cuenta, String cart, String userId, Date date, boolean delivery, String adress,
    Date deliveryDate, boolean entregado, double subtotal, double descuento, double total) {
        this.cuenta = cuenta;
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

	

}

