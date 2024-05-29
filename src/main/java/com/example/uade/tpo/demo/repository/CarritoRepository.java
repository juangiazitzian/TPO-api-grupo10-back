package com.example.uade.tpo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.uade.tpo.demo.entity.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
}
