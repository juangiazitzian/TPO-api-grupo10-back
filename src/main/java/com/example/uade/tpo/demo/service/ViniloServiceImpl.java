package com.example.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.demo.entity.Vinilo;
import com.example.uade.tpo.demo.exceptions.ViniloNotFoundException;
import com.example.uade.tpo.demo.model.ViniloUpdateDTO;
import com.example.uade.tpo.demo.exceptions.ViniloDuplicateException;
import com.example.uade.tpo.demo.repository.ViniloRepository;

import jakarta.transaction.Transactional;

@Service
public class ViniloServiceImpl implements ViniloService {

    @Autowired
    private ViniloRepository viniloRepository;

    @Override
    public Page<Vinilo> getVinilos(PageRequest pageable) {
        return viniloRepository.findAll(pageable);
    }
    
    @Override
    public List<Vinilo> getViniloByTitulo(String title) {
        return viniloRepository.findByTitle(title);
    }

    @Override
    public Optional<Vinilo> getViniloById(Long id) {
        return viniloRepository.findById(id);
    }

    @Transactional(rollbackOn = Throwable.class)
    @Override
    public Vinilo newVinilo(String title, String subtitle, String image, Double price, String genero, int stock) throws ViniloDuplicateException {
        List<Vinilo> vinilos = viniloRepository.findByTitle(title);
        if (vinilos.isEmpty()) {
            Vinilo newVinilo = new Vinilo(title, subtitle, image, price, genero, stock);
            return viniloRepository.save(newVinilo);
        }
        throw new ViniloDuplicateException();
    }
    
    
    @Override
    public Vinilo updateVinilo(Long id, ViniloUpdateDTO viniloUpdateDTO) throws ViniloNotFoundException {
        Vinilo optionalVinilo = viniloRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        viniloUpdateDTO.getStock().ifPresent(optionalVinilo::setStock);
        viniloUpdateDTO.getPrice().ifPresent(optionalVinilo::setPrice);
        viniloUpdateDTO.getImage().ifPresent(optionalVinilo::setImage);
        return viniloRepository.save(optionalVinilo);

    }
    
    @Override
    public void deleteVinilo(Long id) throws ViniloNotFoundException {
        if (viniloRepository.existsById(id)) {
            viniloRepository.deleteById(id);
        } else {
            throw new ViniloNotFoundException();
        }
    }
    
    @Override
    public String getTitle(Long id) {
    	Vinilo vinilo = viniloRepository.findById(id).get();
        return vinilo.getTitle();
    }

    @Override
    public String getSubtitle(Long id) {
        Vinilo vinilo = viniloRepository.findById(id).get();
        return vinilo.getSubtitle();
    }

    @Override
    public String getImage(Long id) {
    	Vinilo vinilo = viniloRepository.findById(id).get();
        return vinilo.getImage();
    }

    @Override
    public Double getPrecio(Long id) {
    	Vinilo vinilo = viniloRepository.findById(id).get();
        return vinilo.getPrice();
    }

    @Override
    public String getGenero(Long id) {
    	Vinilo vinilo = viniloRepository.findById(id).get();
        return vinilo.getGenero();
    }
}
