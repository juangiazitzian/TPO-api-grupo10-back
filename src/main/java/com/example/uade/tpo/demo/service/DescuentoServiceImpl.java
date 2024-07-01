package com.example.uade.tpo.demo.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    
    @Override
    public Optional<Descuento> getDescuentoById(Long id) {
        return descuentoRepository.findById(id);
    }

    @Override
    public Descuento newDescuento(String code, double off) throws DescuentoDuplicateException {
        Descuento vinilo = descuentoRepository.findByCode(code);
        if (vinilo == null) {
            Descuento newDescuento = new Descuento(code, off);
            return descuentoRepository.save(newDescuento);
        }
        throw new DescuentoDuplicateException();
    }
    
    
    @Override
    public Descuento updateDescuento(Long id, String code, double off) throws DescuentoNotFoundException {
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
	public Descuento getDescuentoByCode(String Code) {
		return descuentoRepository.findByCode(Code);
	}
}
