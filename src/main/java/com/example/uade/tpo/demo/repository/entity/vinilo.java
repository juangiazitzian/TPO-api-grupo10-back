package com.example.uade.tpo.demo.repository.entity;


// src/main/java/com/example/vinylstore/entity/Vinyl.java
import lombok.Data;

/* 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;*/

/* 
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
*/
@Data

public class vinilo {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String subtitle;
    private String imageSrc;
    private Double price;
    private String genero;
}
