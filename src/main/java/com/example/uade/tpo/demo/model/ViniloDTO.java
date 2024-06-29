package com.example.uade.tpo.demo.model;
import lombok.Data;
import jakarta.persistence.Embeddable;

@Data
@Embeddable
public class ViniloDTO {
    private Long id;
    private String title;
    private String subtitle;
    private String image;
    private Double precio;
    private String genero;
    private Integer cantidad;

    public ViniloDTO(Long id, String title, String subtitle, String image, Double precio, String genero, Integer cantidad) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.image = image;
        this.precio = precio;
        this.genero = genero;
        this.cantidad = cantidad;
    }
}

