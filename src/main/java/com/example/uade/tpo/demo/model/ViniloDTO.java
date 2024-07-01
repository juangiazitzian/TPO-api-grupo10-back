package com.example.uade.tpo.demo.model;

import jakarta.persistence.Embeddable;

import com.example.uade.tpo.demo.service.ViniloServiceImpl;

@Embeddable
public class ViniloDTO {
	
	private Long viniloId;
	private String title;
	private String subtitle;
	private String image;
	private double precio;
	private String genero;
    private int cantidad = 1;
    
	public ViniloDTO(Long viniloId, String title, String subtitle, String image, double precio, String genero, int cantidad) {
		this.viniloId = viniloId;
		this.title = title;
		this.subtitle = subtitle;
		this.image = image;
		this.precio = precio;
		this.genero = genero;
		this.cantidad = cantidad;
	}
	
	public Long getViniloId() {
		return viniloId;
	}
	public String getTitle() {
		return title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public String getImage() {
		return image;
	}
	public double getPrecio() {
		return precio;
	}
	public String getGenero() {
		return genero;
	}
	public int getCantidad() {
		return cantidad;
	}
	
	public void setViniloId(Long viniloId) {
		this.viniloId = viniloId;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}

