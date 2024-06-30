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
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Factura {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne 
    @JoinColumn(name = "pedidoId")
    private Pedido pedido;
	
	@Column(nullable = false)
	private Long numFactura;

    @Column(nullable = false)
	private Date fechaEmision;
	
	@Column(nullable = false)
	private Date fechaVencimiento;

	
	public Factura() {
	}
	
    public Factura( Pedido pedido, Long numFactura) {
        this.pedido = pedido;
        this.numFactura = numFactura;
        this.fechaEmision = new Date();
        this.fechaVencimiento = getVencimientoDate();

	}
    private Date getVencimientoDate () {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(new Date());
        calendario.add(Calendar.DAY_OF_YEAR, 30);
        return calendario.getTime();
    }
   
}

