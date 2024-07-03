package com.example.uade.tpo.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data

public class Vinilo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String subtitle;

    @Column(nullable = false)
    private String description;

	@Lob
    @Column(columnDefinition="LONGBLOB")
    private byte[] image;

    @Column(nullable = false)
    private Double price;

    @Column
    private String genero;

    @Column
    private int stock;

    public Vinilo() {
    }
    
    public Vinilo(String title, String subtitle,String description, byte[] image, Double price, String genero, int stock) {
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.image = image;
        this.price = price;
        this.genero = genero;
        this.stock = stock;
    }


}