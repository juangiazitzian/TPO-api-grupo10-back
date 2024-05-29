package com.example.uade.tpo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.uade.tpo.demo.exceptiones.ViniloDuplicateException;
import com.example.uade.tpo.demo.repository.entity.Vinilo;

public interface ViniloService {
    public Page<Vinilo> getVinilos(PageRequest pageRequest);

    public Optional<Vinilo> getViniloById(Long viniloId);

    public Vinilo createVinilo(String title, String subtitle, String imageSrc, Double price, String genero) throws ViniloDuplicateException;
}
