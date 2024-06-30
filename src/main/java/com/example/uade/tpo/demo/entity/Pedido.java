package com.example.uade.tpo.demo.entity;
import java.util.Calendar;

import java.util.Date;
import java.util.List;

import com.example.uade.tpo.demo.model.ViniloDTO;

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
public class Pedido {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<ViniloDTO> cart;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Cuenta cuenta;
	
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

	@Column(nullable = false)
	private String metodoPago;
	
	public Pedido() {
	}
	
    public Pedido( List<ViniloDTO> cart, Cuenta cuenta, boolean delivery, String adress, boolean entregado,
    		double subtotal, double descuento, double total, String metodoPago) {
        this.cuenta = cuenta;
        this.cart = cart;
        this.date = new Date();
        this.delivery = delivery;
        this.adress = adress;
        this.deliveryDate = getDeliveryDate(delivery);
        this.entregado = entregado;
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.total = total;
		this.metodoPago = metodoPago;
	}
    
    private Date getDeliveryDate(boolean delivery) {
    	if (delivery) {
    		Calendar calendario = Calendar.getInstance();
    		calendario.setTime(new Date());
    		calendario.add(Calendar.DAY_OF_YEAR, 2);
    		return calendario.getTime();
    	}
    	return null;
    }
}