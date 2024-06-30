package com.example.uade.tpo.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.uade.tpo.demo.entity.Descuento;
import com.example.uade.tpo.demo.entity.Factura;
import com.example.uade.tpo.demo.entity.Pedido;
import com.example.uade.tpo.demo.entity.Vinilo;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long>{
     Optional<Factura> findById(Long id);
}
