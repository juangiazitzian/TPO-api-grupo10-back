package com.example.uade.tpo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.uade.tpo.demo.entity.Vinilo;

import java.util.List;

@Repository
public interface ViniloRepository extends JpaRepository<Vinilo, Long> {
    List<Vinilo> findByTitle(String title);
}
