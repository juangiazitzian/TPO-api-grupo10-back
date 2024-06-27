package com.example.uade.tpo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.uade.tpo.demo.entity.Descuento;

import java.util.List;

@Repository
public interface DescuentoRepository extends JpaRepository<Descuento, Long> {
    List<Descuento> findByCode(String code);
}
