package com.example.uade.tpo.demo.repository;

import com.example.uade.tpo.demo.repository.entity.Vinilo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViniloRepository extends JpaRepository<Vinyl, Long> {
    List<Vinilo> findByTitle(String title);
}
