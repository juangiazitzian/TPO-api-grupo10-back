package com.example.uade.tpo.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Vinilo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String subtitle;

    @Column
    private String image;

    @Column(nullable = false)
    private Double price;

    @Column
    private String genero;

    
    public Vinilo(String title, String subtitle, String image, Double price, String genero) {
        this.title = title;
        this.subtitle = subtitle;
        this.image = image;
        this.price = price;
        this.genero = genero;
    }

    
	public Long getId() {
		return id;
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
	public Double getPrice() {
		return price;
	}
	public String getGenero() {
		return genero;
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
	public void setPrice(Double price) {
		this.price = price;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
}