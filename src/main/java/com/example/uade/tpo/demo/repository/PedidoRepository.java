package com.example.uade.tpo.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.uade.tpo.demo.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query(value = "select p from Pedido p where p.userId = ?1")
    List<Pedido> findByUserId(Long userId);
}
