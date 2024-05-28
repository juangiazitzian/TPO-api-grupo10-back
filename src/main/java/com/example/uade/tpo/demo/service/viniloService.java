package com.example.uade.tpo.demo.service;

import com.example.vinylstore.entity.Vinyl;
import com.example.vinylstore.repository.VinylRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class viniloService {
    
    @Autowired
    private VinylRepository vinylRepository;

    public List<Vinyl> getAllVinyls() {
        return vinylRepository.findAll();
    }

    public Vinyl getVinylById(Long id) {
        return vinylRepository.findById(id).orElse(null);
    }

    public Vinyl saveVinyl(Vinyl vinyl) {
        return vinylRepository.save(vinyl);
    }

    public void deleteVinyl(Long id) {
        vinylRepository.deleteById(id);
    }
}
