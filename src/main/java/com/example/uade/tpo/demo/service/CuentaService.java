package com.example.uade.tpo.demo.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.uade.tpo.demo.entity.Cuenta;
import com.example.uade.tpo.demo.exceptions.CuentaDuplicateException;
import com.example.uade.tpo.demo.exceptions.CuentaNotFoundException;
import com.example.uade.tpo.demo.exceptions.DescuentoUsedException;

public interface CuentaService {
    Page<Cuenta> getCuentas(PageRequest pageRequest);

    static Optional<Cuenta> getCuentaById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCuentaById'");
    }
    
    Optional<Cuenta> getCuentaByUsername(String username);

    Cuenta newCuenta(String name, String lastName, String username, String password) throws CuentaDuplicateException;

    Cuenta updateCuenta(Long id, String name, String lastName, String username, String password) throws CuentaNotFoundException;

    void deleteCuenta(Long id) throws CuentaNotFoundException;

    void addDescuentoUsado(Long cuentaId, String code) throws DescuentoUsedException, CuentaNotFoundException, Exception;
}
