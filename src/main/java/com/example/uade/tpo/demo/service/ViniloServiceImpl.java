package com.example.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.demo.entity.Vinilo;
import com.example.uade.tpo.demo.exceptions.ViniloDuplicateException;
import com.example.uade.tpo.demo.repository.ViniloRepository;

@Service
public class ViniloServiceImpl implements ViniloService {

    @Autowired
    private ViniloRepository viniloRepository;

    @Override
    public Page<Vinilo> getVinilos(PageRequest pageable) {
        return viniloRepository.findAll(pageable);
    }
    
    public List<Vinilo> getViniloByTitulo(String title) {
        return viniloRepository.findByTitle(title);
    }

    @Override
    public Optional<Vinilo> getViniloById(Long id) {
        return viniloRepository.findById(id);
    }

    @Override
    public Vinilo newVinilo(String title, String subtitle, String image, Double price, String genero) throws ViniloDuplicateException {
        List<Vinilo> vinilos = viniloRepository.findByTitle(title);
        if (vinilos.isEmpty()) {
            Vinilo newVinilo = new Vinilo(title, subtitle, image, price, genero);
            return viniloRepository.save(newVinilo);
        }
        throw new ViniloDuplicateException();
    }
}