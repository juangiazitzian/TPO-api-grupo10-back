package com.example.uade.tpo.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    
    
	
}