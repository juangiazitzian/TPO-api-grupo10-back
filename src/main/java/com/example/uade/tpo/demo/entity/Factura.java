package com.example.uade.tpo.demo.entity;

import java.util.Calendar;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Factura {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne 
    @JoinColumn(name = "pedidoId")
    private Pedido pedido;

    @Column(nullable = false)
	private Date fechaEmision;
	
	@Column(nullable = false)
	private Date fechaVencimiento;
	
	public Factura() {
	}
	
    public Factura(Pedido pedido) {
        this.pedido = pedido;
        this.fechaEmision = new Date();
        this.fechaVencimiento = getVencimientoDate();
	}
    
    private Date getVencimientoDate() {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(new Date());
        calendario.add(Calendar.DAY_OF_YEAR, 30);
        return calendario.getTime();
    }

	public Long getId() {
		return id;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
}

