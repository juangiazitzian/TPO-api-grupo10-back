package com.example.uade.tpo.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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

    @Column
    private Integer stock;

    public Vinilo() {
    }
    
    public Vinilo(String title, String subtitle, String image, Double price, String genero, Integer stock) {
        this.title = title;
        this.subtitle = subtitle;
        this.image = image;
        this.price = price;
        this.genero = genero;
        this.stock = stock;
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
	public int getStock() {
		return stock;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	public void setStock(int stock) {
		this.stock = stock;
	}
}