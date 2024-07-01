package com.example.uade.tpo.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Descuento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private double off;

    public Descuento() {
	}
    
	public Descuento(String code, double off) {
		this.code = code;
		this.off = off;
	}

	public Long getId() {
		return id;
	}
	public String getCode() {
		return code;
	}
	public double getOff() {
		return off;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setOff(double off) {
		this.off = off;
	}
}

