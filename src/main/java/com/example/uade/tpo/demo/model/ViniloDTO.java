package com.example.uade.tpo.demo.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import com.example.uade.tpo.demo.service.ViniloServiceImpl;

@Embeddable
@Data

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
	
	
}

