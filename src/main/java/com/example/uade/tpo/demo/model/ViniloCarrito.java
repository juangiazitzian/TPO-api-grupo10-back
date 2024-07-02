package com.example.uade.tpo.demo.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class ViniloCarrito {
	
	private Long viniloId;
    private int cantidad = 1;

	public ViniloCarrito() {
    }
    public ViniloCarrito(Long ViniloId, int cantidad) {
    	this.viniloId = ViniloId;
        this.cantidad = cantidad;
    }
	
	public Long getViniloId() {
		return viniloId;
	}
	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}