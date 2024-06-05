package com.example.uade.tpo.demo.entity;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean isAdmin;
    
    @Column
    private int discount;

    
    public Cuenta() {
	}
    
	public Cuenta(String name, String lastName, String username, String password, int discount) {
		this.name = name;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.isAdmin = false;
		this.discount = discount;
	}

	
	
}

