package com.example.uade.tpo.demo.entity;

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
public class Factura {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "userId")
    private Cuenta cuenta;
	

    @ManyToOne
    @JoinColumn(name = "pedidoId")
    private Cuenta Pedido;

	@Column(nullable = false)
	private String dateEmision;
	
    @Column(nullable = false)
	private String dateVencimiento;

	
	@Column(nullable = false)
	private double subtotal;
	
	@Column(nullable = false)
	private double descuento;
	
	@Column(nullable = false)
	private double total;
	
	public Factura() {
	}

    public Factura( Pedido pedido, Cuenta cuenta, String dateEmision, String dateVencimiento, double subtotal, double descuento, double total) {
        this.cuenta = cuenta;
        this.pedido = pedido;
        this.dateEmision = dateEmision;
        this.dateVencimiento = dateVencimiento;
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.total = total;
	}

}

