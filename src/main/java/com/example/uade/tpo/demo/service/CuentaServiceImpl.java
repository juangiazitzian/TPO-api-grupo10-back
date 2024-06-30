package com.example.uade.tpo.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.demo.entity.Cuenta;
import com.example.uade.tpo.demo.exceptions.CuentaDuplicateException;
import com.example.uade.tpo.demo.exceptions.CuentaNotFoundException;
import com.example.uade.tpo.demo.exceptions.DescuentoUsedException;
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
    public Cuenta newCuenta(String name, String lastName, String username, String password) throws CuentaDuplicateException {
        Optional<Cuenta> existingCuenta = cuentaRepository.findByUsername(username);
        if (existingCuenta.isPresent()) {
            throw new CuentaDuplicateException();
        } else {
            Cuenta newCuenta = new Cuenta(name, lastName, username, password, null);
            return cuentaRepository.save(newCuenta);
        }
    }

    @Override
    public Cuenta updateCuenta(Long id, String name, String lastName, String username, String password) throws CuentaNotFoundException {
        Optional<Cuenta> optionalCuenta = cuentaRepository.findById(id);
        if (optionalCuenta.isPresent()) {
            Cuenta cuenta = optionalCuenta.get();
            cuenta.setName(name);
            cuenta.setLastName(lastName);
            cuenta.setUsername(username);
            cuenta.setPassword(password);
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

    @Override
    public void addDescuentoUsado(Long cuentaId, String code)
            throws DescuentoUsedException, CuentaNotFoundException, Exception {
                Optional<Cuenta> optionalCuenta = cuentaRepository.findById(cuentaId);
                if (optionalCuenta.isPresent()) {
                    Cuenta cuenta = optionalCuenta.get();
                    List<String> descuentos = cuenta.getDescuentosUsados();
                    if (descuentos.contains(code)) {
                        throw new DescuentoUsedException();
                    }
                    descuentos.add(code);
                    cuenta.setDescuentosUsados(descuentos);
                    cuentaRepository.save(cuenta);
                } else {
                    throw new CuentaNotFoundException();
                }
    }
}
