package com.example.uade.tpo.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean isAdmin;
    
    @Column
    private int discount;

    
	public Cuenta(Long id, String name, String lastName, String username, String password, boolean isAdmin,
			int discount) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
		this.discount = discount;
	}

	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getLastName() {
		return lastName;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public int getDiscount() {
		return discount;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
}

