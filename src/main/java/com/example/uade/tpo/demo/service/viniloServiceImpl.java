// src/main/java/com/uade/tpo/demo/service/VinylServiceImpl.java
package com.example.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.demo.repository.entity.vinilo;
import com.uade.tpo.demo.exceptions.VinylDuplicateException;
import com.example.uade.tpo.demo.repository.viniloRepository;
import com.example.uade.tpo.demo.service.ViniloService;

@Service
public class viniloServiceImpl implements ViniloService {

    @Autowired
    private ViniloRepository ViniloReporitory;

    @Override
    public Page<Vinilo> getVinyls(PageRequest pageable) {
        return ViniloReporitory.findAll(pageable);
    }

    @Override
    public Optional<Vinilo> getVinylById(Long vinylId) {
        return ViniloReporitory.findById(vinylId);
    }

    @Override
    public Vinilo createVinilo (String title, String subtitle, String imageSrc, Double price, String genero) throws VinylDuplicateException {
        List<Vinilo> vinyls = ViniloReporitory.findByTitle(title);
        if (vinyls.isEmpty()) {
            return ViniloReporitory.save(new Vinyl(title, subtitle, imageSrc, price, genero));
        }
        throw new VinylDuplicateException();
    }
}
