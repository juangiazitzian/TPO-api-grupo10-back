package com.example.uade.tpo.demo.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.demo.entity.Cuenta;
import com.example.uade.tpo.demo.exceptions.CuentaDuplicateException;
import com.example.uade.tpo.demo.exceptions.CuentaNotFoundException;
import com.example.uade.tpo.demo.repository.CuentaRepository;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public Page<Cuenta> getCuentas(PageRequest pageable) {
        return cuentaRepository.findAll(pageable);
    }

    @Override
    public Optional<Cuenta> getCuentaById(Long id) {
        return cuentaRepository.findById(id);
    }

    @Override
    public Optional<Cuenta> getCuentaByUsername(String username) {
        return cuentaRepository.findByUsername(username);
    }

    @Override
    public Cuenta newCuenta(String name, String lastName, String username, String password, int discount) throws CuentaDuplicateException {
        Optional<Cuenta> existingCuenta = cuentaRepository.findByUsername(username);
        if (existingCuenta.isPresent()) {
            throw new CuentaDuplicateException();
        } else {
            Cuenta newCuenta = new Cuenta(name, lastName, username, password, discount);
            return cuentaRepository.save(newCuenta);
        }
    }

    @Override
    public Cuenta updateCuenta(Long id, String name, String lastName, String username, String password, int discount) throws CuentaNotFoundException {
        Optional<Cuenta> optionalCuenta = cuentaRepository.findById(id);
        if (optionalCuenta.isPresent()) {
            Cuenta cuenta = optionalCuenta.get();
            cuenta.setName(name);
            cuenta.setLastName(lastName);
            cuenta.setUsername(username);
            cuenta.setPassword(password);
            cuenta.setDiscount(discount);
            return cuentaRepository.save(cuenta);
        } else {
            throw new CuentaNotFoundException();
        }
    }

    @Override
    public void deleteCuenta(Long id) throws CuentaNotFoundException {
        if (cuentaRepository.existsById(id)) {
            cuentaRepository.deleteById(id);
        } else {
            throw new CuentaNotFoundException();
        }
    }
}
