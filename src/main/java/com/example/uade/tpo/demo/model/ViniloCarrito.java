package com.example.uade.tpo.demo.model;

import jakarta.persistence.Embeddable;

import com.example.uade.tpo.demo.service.ViniloServiceImpl;

@Embeddable
public class ViniloCarrito {
	
	private Long viniloId;
    private int cantidad = 1;

    public ViniloCarrito(Long ViniloId, int cantidad) {
    	this.viniloId = ViniloId;
        this.cantidad = cantidad;
    }

	public String getTitle() {
		return new ViniloServiceImpl().getTitle(getViniloId());
	}
	public String getSubtitle() {
		return new ViniloServiceImpl().getSubtitle(getViniloId());
	}
	public String getImage() {
		return new ViniloServiceImpl().getImage(getViniloId());
	}
	public Double getPrecio() {
		return new ViniloServiceImpl().getPrecio(getViniloId());
	}
	public String getGenero() {
		return new ViniloServiceImpl().getGenero(getViniloId());
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