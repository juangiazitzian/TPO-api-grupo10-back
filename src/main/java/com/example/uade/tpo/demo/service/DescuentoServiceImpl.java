package com.example.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.demo.entity.Cuenta;
import com.example.uade.tpo.demo.entity.Descuento;
import com.example.uade.tpo.demo.exceptions.DescuentoNotFoundException;
import com.example.uade.tpo.demo.exceptions.DescuentoDuplicateException;
import com.example.uade.tpo.demo.repository.DescuentoRepository;

@Service
public class DescuentoServiceImpl implements DescuentoService {

    @Autowired
    private DescuentoRepository descuentoRepository;

    @Override
    public Page<Descuento> getDescuentos(PageRequest pageable) {
        return descuentoRepository.findAll(pageable);
    }
    
    public List<Descuento> getDescuentoByTitulo(String code) {
        return descuentoRepository.findByCode(code);
    }

    @Override
    public Optional<Descuento> getDescuentoById(Long id) {
        return descuentoRepository.findById(id);
    }

    @Override
    public Descuento newDescuento(String code,Double off) throws DescuentoDuplicateException {
        List<Descuento> vinilos = descuentoRepository.findByCode(code);
        if (vinilos.isEmpty()) {
            Descuento newDescuento = new Descuento(code, off);
            return descuentoRepository.save(newDescuento);
        }
        throw new DescuentoDuplicateException();
    }
    
    
    @Override
    public Descuento updateDescuento(Long id, String code, Double off) throws DescuentoNotFoundException {
        Optional<Descuento> optionalDescuento = descuentoRepository.findById(id);
        if (optionalDescuento.isPresent()) {
            Descuento Descuento = optionalDescuento.get();
            Descuento.setCode(code);
            Descuento.setOff(off);
            return descuentoRepository.save(Descuento);
        } else {
            throw new DescuentoNotFoundException();
        }
    }
    
    @Override
    public void deleteDescuento(Long id) throws DescuentoNotFoundException {
        if (descuentoRepository.existsById(id)) {
            descuentoRepository.deleteById(id);
        } else {
            throw new DescuentoNotFoundException();
        }
    }

	@Override
	public List<Descuento> getDescuentoByCode(String Code) {
		return descuentoRepository.findByCode(Code);
	}
}
