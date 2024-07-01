package com.example.uade.tpo.demo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import com.example.uade.tpo.demo.service.CarritoServiceImpl;

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

    @Column(nullable = false)
    private Long cartId;
    
    @ElementCollection
    private List<String> descuentosUsados;

    public Cuenta() {
	}
    
	public Cuenta(String name, String lastName, String username, String password, Long cartId) {
		this.name = name;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.cartId = cartId;
		this.isAdmin = false;
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
	public Long getCartId() {
		return cartId;
	}
	public List<String> getDescuentosUsados() {
		return descuentosUsados;
	}

	public void setId(Long id) {
		this.id = id;
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
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	public void setDescuentosUsados(List<String> descuentosUsados) {
		this.descuentosUsados = descuentosUsados;
	}

	
}

